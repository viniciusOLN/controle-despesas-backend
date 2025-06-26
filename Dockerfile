# Build
FROM gradle:8.4-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle bootJar --exclude-task test

# Runtime
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
