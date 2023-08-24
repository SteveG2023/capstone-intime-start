FROM openjdk:17
LABEL maintainer="test@neuefische.de"

EXPOSE 8080

ADD backend/target/app.jar app.jar

ENTRYPOINT ["top", "-c","java - /app.jar" ]