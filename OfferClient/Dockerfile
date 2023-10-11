FROM openjdk:8
EXPOSE 8082
ADD target/OfferClient-0.0.1-SNAPSHOT.jar OfferClient-docker.jar
ENTRYPOINT ["java","-jar","OfferClient-docker.jar"]