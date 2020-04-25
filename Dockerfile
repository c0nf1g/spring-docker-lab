FROM openjdk:8

ADD target/spring-docker-lab.jar spring-docker-lab.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "spring-docker-lab.jar", "--spring.datasource.url=jdbc:mysql://docker-mysql:3306/test?useSSL=false&serverTimezone=UTC"]