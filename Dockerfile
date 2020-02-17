FROM openjdk:8-jdk-alpine
COPY build/libs/meli-mutantes.jar meli-mutantes.jar
ENTRYPOINT ["java","-jar","meli-mutantes.jar"]