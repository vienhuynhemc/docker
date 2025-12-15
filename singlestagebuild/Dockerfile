FROM maven:3.9.11-amazoncorretto-25-debian
COPY . .
RUN mvn clean package -DskipTests=true
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "target/singlestagebuild-0.0.1-SNAPSHOT.jar"]