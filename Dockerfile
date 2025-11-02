# ===== Build stage =====
FROM gradle:9.1.0-jdk25 AS build
WORKDIR /app
COPY . .
RUN gradle clean bootJar --no-daemon

# ===== Run stage =====
FROM eclipse-temurin:25-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 7070
ENTRYPOINT ["java","-jar","/app/app.jar"]
