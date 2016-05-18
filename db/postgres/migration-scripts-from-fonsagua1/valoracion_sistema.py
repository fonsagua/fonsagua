        # TODO_ Revisar. valoracion_sistema
        ca.copy('sist_cobros', 'VALOR')
        ca.copy('nivel_serv', 'VALCONSUM')
        ca.copy('agua_suf', 'CANTSUF', ca.siNo2Chb)
        ca.copy('serv_continuo', 'AGUACONT', ca.siNo2Chb)