FROM openjdk:19-jdk-alpine
ARG JAR_FILE=build/libs/Individual_Project-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]