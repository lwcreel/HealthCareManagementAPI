FROM openjdk:8-jdk-alpine
WORKDIR /health-care-api
COPY build.gradle ./
COPY settings.gradle ./
COPY gradlew ./
COPY gradlew.bat ./
COPY src ./src
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]