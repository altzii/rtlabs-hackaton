FROM openjdk:8-jdk-alpine
ADD ./target/gateway-service-1.0-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/gateway-service-1.0-SNAPSHOT.jar"]
EXPOSE 8080