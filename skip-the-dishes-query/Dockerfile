FROM maven:3.5.0-jdk-8

ENV APP_TARGET target
ENV APP skip-the-dishes-query.jar

RUN mkdir -p /opt
COPY ${APP_TARGET}/${APP} /opt

ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java ${JAVA_OPTS} ${CONFIG_RESOURCE} -jar /opt/$APP" ]
