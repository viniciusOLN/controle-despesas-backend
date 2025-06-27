#!/bin/sh

host="mysql"
port=3306

echo "Aguardando o MySQL em $host:$port..."

while ! nc -z $host $port; do
  sleep 1
done

echo "MySQL está pronto. Iniciando a aplicação..."
exec "$@"
