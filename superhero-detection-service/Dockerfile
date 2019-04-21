FROM openjdk:8-jdk-alpine
ADD ./target/superhero-detection-service-1.0-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/superhero-detection-service-1.0-SNAPSHOT.jar"]
EXPOSE 8005