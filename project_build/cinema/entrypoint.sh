#!/bin/bash

set -e

host="db"
port="5432"
bd="fwa"
cmd="$@"

>&2 echo "Check db for available"

#until curl http://"$host":"$port"/"$bd"; do
#  >&2 echo "db is unavailable - sleeping"
  sleep 20
#done

>&2 echo "db is up - executing command"

exec $cmd