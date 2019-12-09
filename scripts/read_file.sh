#!/usr/bin/env bash

# https://stackoverflow.com/questions/10929453/
# https://stackoverflow.com/questions/1521462/
# https://stackoverflow.com/questions/19417015/

# Usage: pass a bash function to this function like
# function foo () {
#    echo "${1}"
# }
# read_file_trimming /tmp/foo.txt foo
read_file() {
    local FILENAME="${1}"
    local callback="${2}"
    while IFS= read -r line || [[ -n "$line" ]]; do
        "${callback}" "${line}"
    done < "${FILENAME}"
}

# Por algún motivo no funciona si se llama desde algo que use `set -e`

# Usage:
# read_file_into_array /tmp/foo.txt 'my_array'
# for line in "${my_array[@]}"; do
#     echo "$line"
# done
#
# my_array is a string. It will be the name of the variable that will contain
# the read values. `read_file_into_array /tmp/foo.txt my_array` can also be
# used but the quotes make it clear.
#
# If used inside a function is better to use
# local my_array
# read_file_into_array /tmp/foo.txt my_array
read_file_into_array() {
    local FILENAME="${1}"
    local -n arr=$2
    arr=()
    i=0
    while IFS= read -r line || [[ -n "$line" ]]; do
        arr[i]="${line}"
        let "i++"
    done < "${FILENAME}"
}
