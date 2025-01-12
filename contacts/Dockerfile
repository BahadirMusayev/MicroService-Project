FROM openjdk:17-jdk-slim
WORKDIR /contacts
COPY ./gradlew .
COPY ./gradle gradle
COPY . .
RUN apt-get update && apt-get install -y wait-for-it
RUN chmod +x gradlew
RUN ./gradlew build
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "build/libs/contacts-0.0.1-SNAPSHOT.jar"]