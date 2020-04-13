FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER shenjian26@hotmail.com

RUN mkdir -p /queen/gateway

WORKDIR /queen/gateway

EXPOSE 80

ADD ./target/queen-gateway.jar ./app.jar

CMD java -Djava.security.egd=file:/dev/./urandom -jar app.jar --spring.profiles.active=test


