FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR /opt/app

EXPOSE 8080 8082

ARG JAR_FILE=target/codebrothers.services.auth-0.0.1-SNAPSHOT.jar

# cp spring-boot-web.jar /opt/app/app.war
COPY ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]
