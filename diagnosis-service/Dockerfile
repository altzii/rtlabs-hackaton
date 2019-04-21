FROM openjdk:8-jdk-alpine
ADD ./target/diagnisis-service-1.0.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/diagnosis-service-1.0.jar"]
EXPOSE 8003