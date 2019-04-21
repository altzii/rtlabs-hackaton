FROM openjdk:8-jdk-alpine
ADD ./target/gateway_service-1.0.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/gateway_service-1.0.jar"]
EXPOSE 8080