FROM openjdk:17
EXPOSE 8081
ADD target/ApiGateway-0.0.1-SNAPSHOT.jar Gateway-docker.jar
ENTRYPOINT ["java", "-jar", "Gateway-docker.jar"]
