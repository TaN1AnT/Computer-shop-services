FROM openjdk:17-alpine

WORKDIR /opt/server
COPY ./target/service-types-0.0.1-SNAPSHOT.jar server2.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "server2.jar"]