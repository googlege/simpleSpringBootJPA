#!/usr/bin/env bash

if [ "$#" -lt 2 ]; then
    echo "Invalid number of arguments! Right usage: $0 <path to data file> <url of rest endpoint> [<delay between requests in s>]"
    exit 1
fi

datafile=$1
restEndpoint=$2
delay=0
if [ -n "$3" ]; then
    delay=$3
fi

while IFS='' read -r line || [[ -n "$line" ]]; do
    echo "Send measure: $line"
    curl -X POST "${restEndpoint}" -H "accept: */*" -H "Content-Type: application/json" -d "${line}"
    sleep ${delay}
done < "${datafile}"
