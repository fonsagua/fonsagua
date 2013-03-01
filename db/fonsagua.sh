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

create-db() {
    $DROPDB -h $server -p $port -U $superuser $dbname;
    $DROPUSER -h $server -p $port -U $superuser $user
    $CREATEUSER -h $server -p $port -U $superuser -SPDRl $user
    $CREATEDB -h $server -p $port -U $superuser -T $template --owner $user $dbname;

    $PSQL -h $server -p $port -U $superuser $dbname -c \
	"ALTER DATABASE $dbname OWNER TO $user; \
         ALTER SCHEMA public OWNER TO $user;
         ALTER TABLE public.geometry_columns OWNER TO $user; \
         ALTER TABLE public.spatial_ref_sys OWNER TO $user;"
}

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
. db_binaries $DB_BINARIES

if [ $schema == "all" ] ; then
    echo "LOG: drop & create database"
    create-db
    for file in `ls ./data/*.sql` ; do
	$PSQL -h $server -U $user -p $port -d $dbname -f $file
    done
    # ./create-db.sh $config_file
fi
