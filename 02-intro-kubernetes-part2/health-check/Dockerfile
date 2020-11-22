FROM maven:3.6.3-openjdk-15 AS build
WORKDIR /home/health-check
COPY src src
COPY pom.xml pom.xml
RUN mvn package

FROM openjdk:15-jdk-alpine
LABEL maintainer="yegor.pilipenko@gmail.com"
COPY --from=build /home/health-check/target/health-check-*.jar /usr/local/lib/health-check.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/health-check.jar", "--server.port=8080"]
