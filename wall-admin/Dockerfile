FROM openjdk:8-jdk-alpine
ARG JAR_FILE=*.jar
RUN apk add --no-cache tzdata \
    && ln -snf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && apk --no-cache add msttcorefonts-installer fontconfig ttf-dejavu \
    && fc-cache -f \
    && echo "Asia/Shanghai" > /etc/timezone
ENV TZ Asia/Shanghai
ENV JAVA_OPTS="\
-server \
-Xms512m \
-Xmx2g \
-XX:+UseG1GC \
-XX:+HeapDumpOnOutOfMemoryError"

COPY ${JAR_FILE} app.jar
ENTRYPOINT java ${JAVA_OPTS} -Djava.security.egd=file:/dev/urandom -Dspring.profiles.active=$CE -jar /app.jar --spring.config.name=application-$CE  -c
EXPOSE 10012