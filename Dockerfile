FROM sbtscala/scala-sbt:graalvm-community-22.0.1_1.10.11_3.6.4 AS builder

WORKDIR /service
COPY . .
RUN cd /service && sbt "show GraalVMNativeImage/packageBin"

FROM debian:bookworm-slim

WORKDIR /service
COPY --from=builder /service/target/graalvm-native-image .
COPY src/main/resources src/main/resources
EXPOSE 3000
CMD ["./No-as-a-Service"]
