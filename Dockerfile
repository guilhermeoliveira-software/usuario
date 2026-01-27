FROM gradle:8-jdk21 AS BUILD

WORKDIR /app

COPY . .

RUN gradle build --no-daemon


FROM amazoncorretto:21-alpine

WORKDIR /app

COPY --from=BUILD /app/build/libs/*.jar /app/usuario.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/usuario.jar"]
