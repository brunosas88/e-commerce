FROM amazoncorretto:11-alpine
EXPOSE 8083
ADD target/usuario-api-0.0.1-SNAPSHOT.jar usuario-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/usuario-api-0.0.1-SNAPSHOT.jar"]