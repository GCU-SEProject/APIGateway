FROM openjdk:17-jdk
VOLUME /tmp
WORKDIR /app
COPY target/apigateway-service-0.0.1-SNAPSHOT.jar apigateway.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "apigateway.jar"]