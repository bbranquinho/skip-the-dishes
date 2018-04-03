FROM maven:3.5.0-jdk-8

ENV APP skip-the-dishes-security-application.jar

RUN mkdir -p /opt
COPY target/${APP} /opt

ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java ${JAVA_OPTS} -jar /opt/$APP" ]
