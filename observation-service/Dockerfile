FROM openjdk:8-jdk-alpine
ADD ./target/observation-service-1.0.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/observation-service-1.0.jar"]
EXPOSE 8004