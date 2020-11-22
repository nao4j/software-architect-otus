FROM maven:3.6.3-openjdk-15 AS build
WORKDIR /home/crud
COPY src src
COPY pom.xml pom.xml
RUN mvn package

FROM openjdk:15-jdk-alpine
LABEL maintainer="yegor.pilipenko@gmail.com"
COPY --from=build /home/crud/target/crud-*.jar /usr/local/lib/crud.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/crud.jar", "--server.port=8080", "--spring.datasource.url=${POSTGRES_URL}", "--spring.datasource.username=${POSTGRES_USER}", "--spring.datasource.password=${POSTGRES_PASSWORD}"]
