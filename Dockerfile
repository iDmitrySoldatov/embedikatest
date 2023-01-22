FROM amazoncorretto:17-alpine-jdk
COPY ./target/embedikatest-0.0.1-SNAPSHOT.jar .
CMD ["java","-jar","embedikatest-0.0.1-SNAPSHOT.jar"]

