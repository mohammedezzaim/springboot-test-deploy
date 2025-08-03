# Stage 1: Build the backend application
FROM maven:3.9.6-eclipse-temurin-17 AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn -f pom.xml clean package -Dmaven.test.skip

# Stage 2: Run the backend application
FROM openjdk:17
COPY --from=build /workspace/target/app-deploy.jar app.jar
COPY zyn/ssl/localhost/keystore.p12 zyn/ssl/localhost/keystore.p12
COPY zyn/ssl/prod/keystore.p12 zyn/ssl/prod/keystore.p12


EXPOSE 8081
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]


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
