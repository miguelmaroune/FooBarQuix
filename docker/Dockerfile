FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/data-batch-processor-0.0.1-SNAPSHOT.jar /app/data-batch-processor-0.0.1.jar

EXPOSE 8080

CMD ["java", "-jar", "data-batch-processor-0.0.1.jar"]
