FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/token-service-0.0.1.jar
ADD ${JAR_FILE} token-service.jar
ENTRYPOINT ["java","-jar","/token-service.jar"]
