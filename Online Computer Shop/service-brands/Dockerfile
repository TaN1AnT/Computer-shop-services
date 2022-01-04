FROM openjdk:17-alpine

WORKDIR /opt/server
COPY ./target/service-brands-0.0.1-SNAPSHOT.jar server1.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "server1.jar"]