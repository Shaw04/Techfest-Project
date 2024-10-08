FROM openjdk:17-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ./target/TechFest-0.0.1-SNAPSHOT.jar Tech.jar
ENTRYPOINT ["java","-jar","/Tech.jar"]