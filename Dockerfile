# Build
FROM gradle:8.4-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle bootJar --exclude-task test

# Runtime
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Instala netcat-openbsd para o wait-for.sh
RUN apt-get update && apt-get install -y netcat-openbsd && rm -rf /var/lib/apt/lists/*

# Copia o JAR da etapa de build
COPY --from=build /app/build/libs/*.jar app.jar

# Copia o script wait-for.sh
COPY wait-for.sh wait-for.sh
RUN chmod +x wait-for.sh

# Usa o script como entrypoint para aguardar o MySQL e iniciar a aplicação
ENTRYPOINT ["./wait-for.sh", "java", "-jar", "app.jar"]
