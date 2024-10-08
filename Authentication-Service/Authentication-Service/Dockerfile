FROM openjdk:17-jdk-alpine
EXPOSE 8081
ARG JAR_FILE=target/*.jar
COPY ./target/Authentication-Service-0.0.1-SNAPSHOT.jar Auth.jar
ENTRYPOINT ["java","-jar","/Auth.jar"]