FROM openjdk:8-jdk-alpine
COPY localstart.sh localstart.sh
COPY setup.sh setup.sh
COPY wait-for wait-for
COPY build/libs/meli-mutantes-lambda.jar meli-mutantes-lambda.jar
RUN apk add --update py-pip && \
	pip install awscli-local
CMD sh localstart.sh
