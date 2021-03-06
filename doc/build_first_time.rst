How to build the first time
===========================
* Configure a valid gvSIG workspace
* Add the following additional projects:

  * extNavtable: https://github.com/navtable/navtable
  * extELLE: http://gitorious.org/elle
  * fonsagua: https://github.com/fonsagua/fonsagua
  * extDBConnection: http://gitorious.org/extdbconnection/

* It's also suggested add extNavtableForms to the workspace. Use this *extNavtableForms* as project name because, this is the name that will be used in the classpath to search for the sources of the navtabforms.jar library.

  * extNavtableForms: https://github.com/navtable/navtableforms

* Compile gvSIG, extNavtable, extELLE and fonsagua
* Configure the file db_config_devel, with the parameters of postgis devel enviroment
* Configure the file config/test.properties with the value of the absolute path to the drivers classes of gvSIG, *_fwAndami/gvSIG/extensiones/com.iver.cit.gvsig/drivers*. This is only used on the tests.
* Execute the script db/fonsagua.sh. This will remove the fonsagua database in your cluster and re-create it with the needed information


Forms devel rules
=================

* ui files: The ui generated by abeille in xml format and located in the *ui* folder.
* metadata files: The domain / rules files that define some of the behaviour of the form. They are located in the *metadata* folder.
* Alias files: Files written in the navtable alias style (java properties). They are located in the *alias* folder, and have .alias as extension. The alias, should be the same as the text of the jlabel that match the widget where the data is introduced.

The name of this files must be the same of the database table. The name of the Java file, should be the name of the table with Form as suffix. As example, for the table comunidades:

ui/comunidades.xml
metadata/comunidades.xml
alias/comunidades.alias
src/**/ComunidadesForm.java

* The form java files must inherit from BasicAbstractForm
* Don't upload a Form without tests. For the test, create a JUnit class that inherits from CommonMethodsForTestDBForms. 


TODO
====
* Create a script / ant target that allows checks that the needed dependencies are configured on the workspace. Also this should compile all the projects that are not from gvSIG
* Add to *build first time" the use of the ant script.
