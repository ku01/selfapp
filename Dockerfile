FROM gradle:8.6.0-jdk21-jammy AS build
WORKDIR /home/gradle/src
ADD ./build.gradle.kts build.gradle.kts
ADD ./settings.gradle.kts settings.gradle.kts
ADD ./src src
RUN gradle -i --no-daemon clean build --stacktrace

FROM bellsoft/liberica-openjdk-alpine:21-37
COPY --from=build /home/gradle/src/build/libs/app.jar app.jar
EXPOSE 8080
EXPOSE 8081
ENTRYPOINT java -jar app.jar
