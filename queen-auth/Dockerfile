FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER shenjian26@hotmail.com

RUN mkdir -p /queen/auth

WORKDIR /queen/auth

EXPOSE 8010

ADD ./target/queen-auth.jar ./app.jar

CMD java -Djava.security.egd=file:/dev/./urandom -jar app.jar --spring.profiles.active=test
