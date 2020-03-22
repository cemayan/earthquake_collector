FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/db-service-0.0.1.jar
ADD ${JAR_FILE} db-service.jar
RUN apk add --no-cache bash
ENTRYPOINT ["java","-jar","/db-service.jar"]