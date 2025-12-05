### Multi-stage Dockerfile for building and running the Spring Boot application
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY pom.xml mvnw . /
COPY .mvn .mvn
COPY src src
RUN mvn -B -DskipTests package

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
ENV PORT 8080
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE ${PORT}
ENTRYPOINT ["sh","-c","java -jar /app/app.jar --server.port=${PORT}"]
