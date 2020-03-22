FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/test-0.0.1.jar
ADD ${JAR_FILE} test.jar
ENTRYPOINT ["java","-jar","/test.jar"]