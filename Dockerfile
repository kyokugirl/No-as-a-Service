FROM sbtscala/scala-sbt:eclipse-temurin-alpine-23.0.2_7_1.10.11_3.6.4

WORKDIR /service
COPY . .
RUN cd /service && sbt compile
EXPOSE 3000
CMD sbt run
