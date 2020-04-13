FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER shenjian26@hotmail.com

RUN mkdir -p /queen/user

WORKDIR /queen/user

EXPOSE 8102

ADD ./target/queen-user.jar ./app.jar

CMD java -Djava.security.egd=file:/dev/./urandom -jar app.jar --spring.profiles.active=test
