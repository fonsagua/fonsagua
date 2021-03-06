#!/bin/bash

usage() {
    echo
    echo "`basename $0` -c config_file -s schema_to_deploy -b path_to_postgresql_bin"
    echo
    echo "-c: config file"
    echo "-s: schema to deploy (consultas, elle, infobase, inventario). 'all' will recreate the whole database"
    echo "-b: folder where dropdb, psql, ... are located if they are not in the path"
    exit -1
}

error() {
    echo $1
    exit
}

# CHECK BOM FILES
# http://stackoverflow.com/questions/204765/elegant-way-to-search-for-utf-8-files-with-bom
# PRINT BOM FILENAME: find . -type f -print0 | xargs -0r awk '/^\xEF\xBB\xBF/ {print FILENAME}
# REMOVE BOM: sed -i '1s/^\xEF\xBB\xBF//' BOM_FILE_NAME
find . -type f -print0 | xargs -0r awk '/^\xEF\xBB\xBF/ {print FILENAME}{nextfile}' | wc -l | egrep '^0$' || error 'BOM FILES'

. create-db.sh
. create-pgpass.sh
# comment the following lines if you prefer
# to force the use of the arguments
config_file=./db_config_devel

schema=all


while getopts ":c:s:b:r" opt; do
  case $opt in
    c)
      #If config file exists, load it; else, exit.
      if [ -e $OPTARG ]
      then
          echo "LOG: config file="$OPTARG
          config_file=$OPTARG
      else
          echo "ERROR: file "$OPTARG" not exist"
          usage
      fi
      ;;
    s)
      #Use all (recreate the whole database) if schema is not set
      if [ -z $OPTARG ]
      then
          schema=all
      else
          schema=$OPTARG
      fi
          echo "LOG: schema="$schema
      ;;
    b)
	  DB_BINARIES=$OPTARG
      ;;
    r)
      REAL=1
      ;;
    \?)
      echo "ERROR: Option" $OPTARG "not available"
      usage
      ;;
    :)
      echo "ERROR: Option" $OPTARG "requires an argument"
      usage
      ;;
  esac
done

#Check schema & config_file are set
if [ -z $schema ] ; then
    echo "ERROR: schema not set"
    usage
fi

if [ -z $config_file ] ; then
    echo "ERROR: config file not set"
    usage
fi

. $config_file
create-pgpass $config_file

. db_binaries $DB_BINARIES

if [ $schema == "all" ] ; then
    echo "LOG: drop & create database"
    create-db || error

    for file in `ls ./data/*.sql` ; do
	$PSQL -h $server -U $user -p $port -d $dbname -f $file || error "Procesando fichero $file"
    done

    if [ $config_file == "./db_config_devel" ] ; then
	$PSQL -h $server -U $user -p $port -d $dbname -f ../data-test/20130610-fonsagua-test-data.sql
	$PSQL -h $server -U $superuser -p $port -d $dbname -f ../data-test/20130829-alternativas-test-data.sql
    fi
    # ./create-db.sh $config_file
fi

$PSQL -h $server -U $superuser -p $port -d $dbname -c "VACUUM ANALYZE;"

# Check BOM files

if hash pg_prove 2>/dev/null ; then
    psql -U $superuser -d $dbname -c "CREATE EXTENSION pgtap;"
    pg_prove -U $superuser -d $dbname -h $server ./tests/*.sql
else 
    echo >&2 "pg_prove not installed db tests skipped"
fi


delete-pgpass $config_file

# ./pg_dump -U postgres --schema elle --inserts fonsagua --file=/tmp/01-schema-elle.sql