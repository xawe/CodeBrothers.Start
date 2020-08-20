FROM adoptopenjdk/openjdk11:alpine-jre

ENV APP_NAME codebrothers-services-auth
ENV APP_HOME /opt/codebrothers
ENV LANG en_US.UTF-8
ENV JAVA_OPTS ''

WORKDIR $APP_HOME

EXPOSE 8082

LABEL SERVICE_NAME=$APP_NAME

ADD target/*.jar ${APP_HOME}/${APP_NAME}.jar

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -Dapp=${APP_NAME} -jar ${APP_NAME}.jar"]