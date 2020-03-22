FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/user-config-0.0.1.jar
ADD ${JAR_FILE} user-config.jar
ENTRYPOINT ["java","-jar","/user-config.jar"]
