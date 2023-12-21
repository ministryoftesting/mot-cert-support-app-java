FROM openjdk:18-slim

ADD . /usr/local/application

WORKDIR /usr/local/report

COPY target/mot-cert-support-app-java-*-exec.jar ./

ENTRYPOINT java -jar mot-cert-support-app-java-*-exec.jar -D
