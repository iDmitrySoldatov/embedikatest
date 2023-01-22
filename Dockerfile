FROM bellsoft/liberica-openjdk-alpine-musl

COPY ./target/embedikatest-0.0.1-SNAPSHOT.jar .

CMD ["java","-jar","embedikatest-0.0.1-SNAPSHOT.jar"]


#FROM openjdk:17
#COPY ./target/embedikatest-0.0.1-SNAPSHOT.jar /usr/src/myapp
#WORKDIR /usr/src/myapp
#CMD ["java","-jar","embedikatest-0.0.1-SNAPSHOT.jar"]

