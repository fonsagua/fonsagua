create-pgpass() {
    #$1 = configuration file
    if [ $1 == "./db_config_devel" ]; then
        export PGPASSFILE=./pgpass
        echo "${server}:${port}:*:${superuser}:${superuserpwd}" > "./pgpass"
        echo "${server}:${port}:*:${user}:${userpwd}" >> "./pgpass"
        chmod 0600 ./pgpass
    fi
}

delete-pgpass() {
    if [ $1 == "./db_config_devel" ]; then
        rm ./pgpass
    fi
}
