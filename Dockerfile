# Start from an OpenJDK base image
FROM openjdk:21-jdk-slim

# Add a volume to store logs or temp data
VOLUME /tmp

# Set working directory
WORKDIR /app

# Copy the Spring Boot jar into the container
COPY target/parcInfoAPI-0.0.1-SNAPSHOT.jar api.jar

# Expose the application port
EXPOSE 8082

# Run the jar file
ENTRYPOINT ["java", "-jar", "api.jar"]
