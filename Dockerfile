FROM openjdk:17-jdk-alpine

EXPOSE 8081

ADD target/SpringBoot_1-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]