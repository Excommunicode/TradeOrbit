FROM amazoncorretto:21-alpine-jdk
COPY target/*.jar authorization.jar
ENTRYPOINT ["java","-jar","/authorization.jar"]