FROM openjdk:8-jdk-alpine
ADD ./target/diagnosis-service-1.0-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/diagnosis-service-1.0-SNAPSHOT.jar"]
EXPOSE 8003
