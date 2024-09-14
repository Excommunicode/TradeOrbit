FROM amazoncorretto:21-alpine-jdk
COPY target/*.jar central.jar
ENTRYPOINT ["java","-jar","/central.jar"]