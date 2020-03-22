#FROM maven:3.5.2-jdk-8-alpine AS build
#COPY . /tmp/analytics-engine/
#WORKDIR /tmp/analytics-engine/
#RUN mvn clean package

FROM openjdk:8-jdk-alpine
ARG JAR_FILE=analytics-engine/target/analytics-engine-0.0.1.jar
ADD ${JAR_FILE} analytics-engine.jar
ENTRYPOINT ["java","-jar","/analytics-engine.jar"]
EXPOSE 8090