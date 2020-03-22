FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/service-discovery-server-0.0.1.jar
ADD ${JAR_FILE} service-discovery-server.jar
ENTRYPOINT ["java","-jar","/service-discovery-server.jar"]