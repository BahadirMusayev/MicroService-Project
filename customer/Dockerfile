FROM openjdk:17-jdk-slim
WORKDIR /customer
COPY ./gradlew .
COPY ./gradle gradle
COPY . .
RUN apt-get update && apt-get install -y wait-for-it
RUN chmod +x gradlew
RUN ./gradlew build
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "build/libs/customer-0.0.1-SNAPSHOT.jar"]