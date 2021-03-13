FROM openjdk:8-jdk-alpine
MAINTAINER "sb" <stanislawbartkowski@gmail.com>
ARG RESTPORT

COPY target/MockRestService-1.0-SNAPSHOT-jar-with-dependencies.jar .
EXPOSE ${RESTPORT}
ENV RESTPORT=${RESTPORT}
ENTRYPOINT java -cp MockRestService-1.0-SNAPSHOT-jar-with-dependencies.jar com.org.mockrestservice.MockRestService -p ${RESTPORT}
