FROM maven:3.9.11-amazoncorretto-25-debian AS builder
COPY . .
RUN mvn clean package -DskipTests=true

FROM eclipse-temurin:25
COPY --from=builder "target/named-volume-0.0.1-SNAPSHOT.jar" .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "named-volume-0.0.1-SNAPSHOT.jar"]