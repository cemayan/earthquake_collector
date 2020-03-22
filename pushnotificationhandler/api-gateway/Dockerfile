FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/api-gateway-0.0.1.jar
ADD ${JAR_FILE} api-gateway.jar
RUN apk add --no-cache bash
ENTRYPOINT ["java","-jar","/api-gateway.jar"]
EXPOSE 8762