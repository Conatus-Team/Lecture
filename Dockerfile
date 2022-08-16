FROM adoptopenjdk/openjdk11

ENV JAVA_OPTS=""

RUN mkdir -p /root/.ssh
RUN chmod 700 /root/.ssh

RUN mkdir -p /moine   \
             /Logs


COPY /target/*SNAPSHOT.jar app.jar
EXPOSE 8080
USER root
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENTRYPOINT ["java","-Xmx400M","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--Dspring.profiles.active=aws"]