FROM openjdk:17

RUN mkdir /app

RUN mvn clean install

COPY /target/enterprise-challenge-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

ENTRYPOINT [ "java", "-jar", "/app/app.jar" ]

EXPOSE 80
