FROM openjdk:8-jdk-alpine
ADD ./target/observation-service-1.0-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/observation-service-1.0-SNAPSHOT.jar"]
EXPOSE 8004