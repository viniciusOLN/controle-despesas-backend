#!/bin/sh

# Espera até conseguir conectar na porta do banco
echo "Aguardando MySQL em $HOST:$PORT..."

while ! timeout 1 bash -c "echo > /dev/tcp/$HOST/$PORT" 2>/dev/null; do
  sleep 1
done

echo "MySQL está pronto! Iniciando aplicação..."
exec "$@"
