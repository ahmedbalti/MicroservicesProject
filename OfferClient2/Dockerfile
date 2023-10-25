FROM openjdk:17
EXPOSE 8082
ADD target/OfferClient2-0.0.1-SNAPSHOT.jar OfferMicro-docker.jar
ENTRYPOINT ["java", "-jar", "OfferMicro-docker.jar"]
