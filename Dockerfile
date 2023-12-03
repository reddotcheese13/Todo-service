# Use the official Maven image to build the application
FROM maven:3.8.1-openjdk-17 AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Build the application and explicitly set the JAR file name
RUN mvn clean package -DskipTests -DfinalName=TodoApplication

# Use the official OpenJDK Alpine base image for the runtime image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/TodoApplication.jar ./TodoApplication.jar

# Expose the port your application runs on
EXPOSE 8080

# Specify the command to run on container start
CMD ["java", "-jar", "TodoApplication.jar"]
