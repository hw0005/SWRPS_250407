FROM maven:3.9.9-amazoncorretto-21-debian-bookworm AS builder

WORKDIR /usr/src/rpsgame
COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY . .
RUN mvn package -DskipTests

# app
FROM amazoncorretto:21.0.4

WORKDIR /app
COPY --from=builder /usr/src/rpsgame/target/rpsgame-0.0.1.jar .

ENTRYPOINT ["java", "-jar", "/app/rpsgame-0.0.1.jar"]