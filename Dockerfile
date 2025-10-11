# =============================================================================
# Multi-stage Dockerfile for Spring Boot Library Management System
# Java 21 | Spring Boot 3.3.1 | MySQL | MongoDB | Redis
# =============================================================================

# -----------------------------------------------------------------------------
# Stage 1: Build Stage - Compile and package the application
# -----------------------------------------------------------------------------
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build

# Set working directory
WORKDIR /build

# Copy Maven wrapper and pom.xml first for better layer caching
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# Download dependencies (cached layer if pom.xml doesn't change)
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application (skip tests for faster builds, run tests in CI/CD)
# Extract the built JAR name for verification
RUN ./mvnw clean package -DskipTests -B && \
    ls -lh /build/target/*.jar

# -----------------------------------------------------------------------------
# Stage 2: Runtime Stage - Minimal production image
# -----------------------------------------------------------------------------
FROM eclipse-temurin:21-jre-alpine

# Install curl for healthchecks (optional but recommended)
RUN apk add --no-cache curl

# Create non-root user for security best practices
RUN addgroup -S spring && adduser -S spring -G spring

# Set working directory
WORKDIR /app

# Copy the JAR from build stage
COPY --from=build /build/target/*.jar app.jar

# Change ownership to non-root user
RUN chown -R spring:spring /app

# Switch to non-root user
USER spring:spring

# Expose application port
EXPOSE 8080

# JVM optimization flags for containerized environments
ENV JAVA_OPTS="-XX:+UseContainerSupport \
    -XX:MaxRAMPercentage=75.0 \
    -XX:InitialRAMPercentage=50.0 \
    -XX:+UseG1GC \
    -XX:+OptimizeStringConcat \
    -Djava.security.egd=file:/dev/./urandom \
    -Dspring.profiles.active=prod"

# Health check configuration
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
