import collections
import logging

from ietl import geopandas_utils, pandas_utils


# Tal y como está esto sólo funciona cuando hay una sóla columna funcionando como pk
# pero pueden tener distintos nombres en un csv y en el otro
common_left_field = "codigo_comunidad"
common_right_field = "codigo_comunidad"

sqlite_path = "191208_fonsagua_todos_municipios.sqlite"

logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)


def read_csv():
    # Path a los ficheros .csv
    left_path = "nasmar.csv"
    right_path = "nasmar_dd.csv"

    # Opciones de lectura del csv
    csv_options = {
        "sep": ";",
        "header": 0,
        "index_col": "codigo_comunidad",  # Mejor ser explicitos y usar lo que se quiera o None
        "skiprows": [1],
        "quotechar": '"',
        "na_values": [""],
        "dtype": {"codigo_comunidad": object, "cod_municipio": object},
        "skipinitialspace": False,  # Sólo elimina los "leading" y no los trailing spaces, así que es mejor no usarlo
    }

    left = pandas_utils.read_csv(left_path, csv_options)
    pandas_utils.strip_from_headers(left)

    right = pandas_utils.read_csv(right_path, csv_options)
    pandas_utils.strip_from_headers(right)

    if csv_options["index_col"]:
        # https://stackoverflow.com/questions/35368645
        # https://pbpython.com/pandas_dtypes.html
        left.index = left.index.map(str)
        right.index = right.index.map(str)
    return left, right


def adjust_code_to_9_from_8(df, column):
    df[column] = df[column].str[:6] + df[column].str[6:].str.zfill(3)


def number_of_elements_by_council(df, label):
    values = get_column_or_index_values(df, label)
    d = collections.Counter(values.str[0:4])
    return d


def get_column_or_index_values(df, label):
    # df.get(label, df.index)
    values = None
    if label in df.columns:
        values = df[label]
    if label == df.index.name:
        values = df.index
    if values is None:
        raise Exception(f"No se encuentra: {label}")
    return values


def drop_elements_not_in_nasmar_councils(df, column, raise_error=False, log_warn=True):
    """
    # Contamos el número de elementos del df que tenemos por municipio
    # Comprobamos que elementos tenemos que no son de un municipio de NASMAR y los eliminamos
    # Volvemos a hacer el conteo
    """

    from municipios_utils import get_municipios_nasmar_codigos

    municipios_nasmar_codigos = get_municipios_nasmar_codigos()

    d = number_of_elements_by_council(df, column)
    municipios_de_los_elementos = set(d.keys())
    municipios_faltantanes_o_sobrantes = set(
        municipios_nasmar_codigos
    ).symmetric_difference(municipios_de_los_elementos)

    if municipios_faltantanes_o_sobrantes and raise_error:
        raise Exception(
            "Hay municipios faltantes o sobrantes", municipios_faltantanes_o_sobrantes
        )

    if municipios_faltantanes_o_sobrantes and log_warn:
        logger.warning(
            f"Hay municipios faltantes o sobrantes. {municipios_faltantanes_o_sobrantes}"
        )

    values = get_column_or_index_values(df, column)
    condicion_municipios_no_nasmar = ~values.str[0:4].isin(municipios_nasmar_codigos)
    df.drop(df[condicion_municipios_no_nasmar].index, inplace=True)
    number_of_elements_by_council(df, column)


def get_departamentos_bd():
    departamentos_cols = {"cdpto": "codigo", "dpto": "nombre"}
    departamentos = geopandas_utils.get_dataframe_from_spatialite_table(
        sqlite_path,
        "departamentos",
        use_and_rename_cols=departamentos_cols,
        crs={"init": "epsg:32616"},
    )
    departamentos.sort_values(by="codigo", axis="index", ascending=True, inplace=True)
    return departamentos


def get_municipios_bd():
    # Obtenemos la capa de municipios de la bbdd
    municipios_cols = {"cod_munic": "codigo", "munic": "nombre"}
    municipios = geopandas_utils.get_dataframe_from_spatialite_table(
        sqlite_path,
        "municipios",
        use_and_rename_cols=municipios_cols,
        crs={"init": "epsg:32616"},
    )
    municipios.sort_values(by="codigo", axis="index", ascending=True, inplace=True)
    return municipios


def get_municipios_nasmar():
    municipios = get_municipios_bd()
    drop_elements_not_in_nasmar_councils(
        municipios, "codigo", raise_error=False, log_warn=False
    )
    municipios.sort_values(by="codigo", axis="index", ascending=True, inplace=True)
    return municipios


def plot_municipios(municipios=None, _municipios_plot_options={}, labels=True, ax=None):
    if municipios is None:
        municipios = get_municipios_bd()
    default_municipios_plot_options = {
        "edgecolor": "lightgrey",  # "black"
        "color": "whitesmoke",  # None,
        "alpha": 0.5,
        "linewidth": 0.5,  # None
        # "column": "nombre",
    }
    municipios_plot_options = {}
    municipios_plot_options.update(default_municipios_plot_options)
    municipios_plot_options.update(_municipios_plot_options)
    municipios_ax = municipios.plot(figsize=(12, 12), ax=ax, **municipios_plot_options)
    # municipios_ax.set_axis_off()
    if labels:
        geopandas_utils.plot_polygon_labels(municipios, "nombre", {"fontsize": 8})
    return municipios_ax


def get_aldeas_bd():
    aldeas_cols = {"cod_canton": "codigo", "canton": "nombre"}
    aldeas = geopandas_utils.get_dataframe_from_spatialite_table(
        sqlite_path,
        "cantones",
        use_and_rename_cols=aldeas_cols,
        crs={"init": "epsg:32616"},
    )
    aldeas.geometry = aldeas.geometry.buffer(0)  # Para corregir un self-intersection
    aldeas.sort_values(by="codigo", axis="index", ascending=True, inplace=True)
    return aldeas


def get_caserios_bd(caserios_use_cols=None):
    # Comentarios sobre las columnas de la tabla
    # Las columnas "xx_codigo" de la tabla son códigos parciales en tipo float. No usar
    # municipio, departamen, aldea son los nombres de esas unidades administrativas
    # cod_caseri = '17091007'
    # cod_dpto = '17'
    # cod_muni = '1709'
    # cod_aldea = '170910'
    # caserio = nombre del caserio/comunidad
    # categoria toma los valores: {Cabecera Departamental, Caserío, Aldea, Cabecera Municipal}
    # hay datos de población segmentada por sexo y edad.
    # otras columnas que podrían ser útiles
    # 'sex_h', 'sex_m', 'ed_0a10', 'ed_11a20', 'ed_21a30', 'ed_31a40', 'ed_41a50', 'ed_51a60'
    if not caserios_use_cols:
        caserios_use_cols = [
            "cod_caseri",
            "caserio",
            "ed_61a70",
            "ed_71a80",
            "ed_81a90",
            "ed_91a100",
            "ed__101",
            "pob_total",
            "categoria",
        ]
    caserios_cols = {
        "cod_caseri": "codigo",
        "caserio": "nombre",
        "categoria": "categoria",
    }
    caserios = geopandas_utils.get_dataframe_from_spatialite_table(
        sqlite_path,
        "caserios_comunidades",
        caserios_use_cols,
        crs={"init": "epsg:32616"},
    )
    caserios.rename(columns=caserios_cols, inplace=True)

    # if "ed_61a70" in caserios.columns
    # Nos puede valer como comparativa. Nuestra columna tot_ancianos es como la suma de estas
    caserios["tot_ancianos"] = caserios.loc[:, "ed_61a70":"ed__101"].sum(axis=1)
    caserios.drop(
        columns=["ed_61a70", "ed_71a80", "ed_81a90", "ed_91a100", "ed__101"],
        inplace=True,
    )

    # Ajustamos el código de los caserios a 9 caracteres en lugar de a 8
    adjust_code_to_9_from_8(caserios, "codigo")
    caserios.sort_values(by="codigo", axis="index", ascending=True, inplace=True)
    return caserios


def get_caserios_nasmar():
    """
    Devuelve los caserios del INE que están en un municipio de NASMAR
    """
    caserios = get_caserios_bd()
    drop_elements_not_in_nasmar_councils(
        caserios, "codigo", raise_error=False, log_warn=False
    )
    caserios.sort_values(by="codigo", axis="index", ascending=True, inplace=True)
    return caserios


def get_comunidades_bd():
    comunidades_index_col = None  # "cod_comunidad"
    comunidades = geopandas_utils.get_dataframe_from_spatialite_table(
        sqlite_path, "comunidades", index_col=comunidades_index_col
    )
    # Ajustamos el código a 9 caracteres en lugar de a 8
    adjust_code_to_9_from_8(comunidades, "cod_comunidad")
    drop_elements_not_in_nasmar_councils(comunidades, "cod_comunidad", raise_error=True)
    comunidades.sort_values(
        by="cod_comunidad", axis="index", ascending=True, inplace=True
    )
    return comunidades


def get_comunidades_min():
    """
    Devuelve sólo algunas columnas de la tabla de comunidades de la base de datos
    """
    comunidades = get_comunidades_bd()
    comunidades_min = comunidades[
        [
            "cod_comunidad",
            "comunidad",
            "municipio",
            "n_habitantes",
            "tot_ancianos",
            "geom",
        ]
    ]
    return comunidades_min
