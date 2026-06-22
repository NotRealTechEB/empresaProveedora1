# Etapa 1: build con Maven (sin Wrapper)
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copiar pom.xml y descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar código y compilar
COPY src ./src
RUN mvn clean package -DskipTests -B

# Etapa 2: runtime liviano (solo JRE)
FROM eclipse-temurin:21-jre AS runtime
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT:-8080}"]