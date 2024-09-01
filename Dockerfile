FROM openjdk:21-jdk-slim AS build

WORKDIR /app

RUN apt-get update && apt-get install -y maven

COPY pom.xml ./
COPY src ./src

RUN mvn dependency:go-offline -B
RUN mvn package -DskipTests

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=build /app/target/mobiauto-server-1.0.0.jar app.jar

EXPOSE 8081

ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/mobiauto_teste_db?useSSL=false&serverTimezone=UTC
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=root
ENV JWT_SECRET=secrettokenkey
ENV JWT_EXPIRATION=604800000

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=classpath:/application.properties"]
