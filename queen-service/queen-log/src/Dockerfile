FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER shenjian26@hotmail.com

RUN mkdir -p /queen/log

WORKDIR /queen/log

EXPOSE 8103

ADD ./target/queen-log.jar ./app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

CMD ["--spring.profiles.active=test"]
