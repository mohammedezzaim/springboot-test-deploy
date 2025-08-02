# Stage 1 : Build avec Maven
FROM maven:3.9.2-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Stage 2 : Runtime léger OpenJDK
FROM openjdk:17-alpine

WORKDIR /app

COPY --from=build /app/target/app-deploy.jar app.jar

EXPOSE 8080

CMD ["java", "-Xms256m", "-Xmx512m", "-jar", "app.jar"]
