FROM openjdk:8

ADD target/spring-docker-lab.jar spring-docker-lab.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "spring-docker-lab.jar"]