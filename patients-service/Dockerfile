FROM openjdk:8-jdk-alpine
ADD ./target/patients-service-1.0-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/patients-service-1.0-SNAPSHOT.jar"]
EXPOSE 8002