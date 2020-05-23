FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR /opt/app

EXPOSE 8080 8761

ARG JAR_FILE=target/com.codebrothers.infrastructure.eureka-0.0.1-SNAPSHOT.jar

# cp spring-boot-web.jar /opt/app/app.war
COPY ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]
