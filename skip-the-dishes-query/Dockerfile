FROM openjdk:8-jre-alpine

ENV APP_TARGET target
ENV APP skip-the-dishes-query.jar

RUN mkdir -p /opt
COPY ${APP_TARGET}/${APP} /opt

CMD exec java ${JAVA_OPTS} -Xms${JAVA_XMS:-512m} -Xmx${JAVA_XMX:-1024m} -jar /opt/${APP}
