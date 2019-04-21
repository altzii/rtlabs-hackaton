FROM openjdk:8-jdk-alpine
ADD ./target/registry-service-1.0.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/registry-service-1.0.jar"]
EXPOSE 8001