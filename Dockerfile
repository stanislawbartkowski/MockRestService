FROM openjdk:8-jdk-alpine
MAINTAINER "sb" <stanislawbartkowski@gmail.com>
ARG RESTPORT
ARG SECURE

COPY target/MockRestService-1.0-SNAPSHOT-jar-with-dependencies.jar .
EXPOSE ${RESTPORT}
ENV RESTPORT=${RESTPORT}
ENV SECURE=${SECURE}
ENTRYPOINT java -cp MockRestService-1.0-SNAPSHOT-jar-with-dependencies.jar com.org.mockrestservice.MockRestService -p ${RESTPORT} ${SECURE}
