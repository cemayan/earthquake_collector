FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/push-service-0.0.1.jar
ADD ${JAR_FILE} push-service.jar
ENTRYPOINT ["java","-jar","/push-service.jar"]