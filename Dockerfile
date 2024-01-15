FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY apps /app/apps
COPY contexts /app/contexts
RUN mvn -B package -DskipTests

FROM openjdk:17-alpine
COPY --from=build /app/apps/service-presentation/target/Price-Service.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
