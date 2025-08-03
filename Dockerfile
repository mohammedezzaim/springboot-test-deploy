# Étape 1 : Construction de l'application
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2 : Exécution
FROM eclipse-temurin:17-jre-alpine

# Configuration pour Railway
WORKDIR /app
COPY --from=build /workspace/target/app-deploy.jar .
COPY zyn/ssl/localhost/keystore.p12 ./keystore-localhost.p12
COPY zyn/ssl/prod/keystore.p12 ./keystore-prod.p12

# Variables d'environnement pour désactiver les métriques problématiques
ENV JAVA_TOOL_OPTIONS="-Djava.security.egd=file:/dev/./urandom \
                      -Dmanagement.metrics.binders.processor.enabled=false \
                      -Dmanagement.tomcat.metrics.enabled=false"

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app-deploy.jar"]


# # Stage 1 : Build avec Maven
# FROM maven:3.9.2-eclipse-temurin-17 AS build
#
# WORKDIR /app
#
# COPY pom.xml .
# COPY src ./src
#
# RUN mvn clean package -DskipTests
#
# # Stage 2 : Runtime léger OpenJDK
# FROM openjdk:17-alpine
#
# WORKDIR /app
#
# COPY --from=build /app/target/app-deploy.jar app.jar
#
# EXPOSE 8081
#
# CMD ["java", "-Xms256m", "-Xmx512m", "-jar", "app.jar"]
