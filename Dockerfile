FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY . /app
EXPOSE 8080
ENTRYPOINT ["mvn","spring-boot:run"]