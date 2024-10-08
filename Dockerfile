FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.2-jdk-slim
COPY --from=build /target/parceltracking-0.0.1-SNAPSHOT.jar parceltracking.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","parceltracking.jar"]