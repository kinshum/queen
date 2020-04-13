FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER shenjian26@hotmail.com

RUN mkdir -p /queen/desk

WORKDIR /queen/desk

EXPOSE 8105

ADD ./target/queen-desk.jar ./app.jar

CMD java -Djava.security.egd=file:/dev/./urandom -jar app.jar --spring.profiles.active=test
