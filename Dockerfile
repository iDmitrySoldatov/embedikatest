FROM bellsoft/liberica-openjdk-alpine-musl
COPY ./target/embedikatest-0.0.1-SNAPSHOT.jar .
CMD ["java","-jar","embedikatest-0.0.1-SNAPSHOT.jar"]

