# Base image
FROM openjdk:17-jdk-slim

# Application JAR file
ARG JAR_FILE=build/libs/review-0.0.1-SNAPSHOT.jar

# Copy JAR file to container
COPY ${JAR_FILE} app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
