FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app

COPY gradlew .
COPY gradle/ gradle/
COPY build.gradle.kts .
COPY gradle.properties* ./

COPY account/build.gradle.kts account/
COPY account/src/ account/src/

RUN echo 'rootProject.name = "selfapp-account"' > settings.gradle.kts && \
    echo 'include("account")' >> settings.gradle.kts

RUN chmod +x ./gradlew
RUN ./gradlew :account:bootJar --no-daemon

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

RUN addgroup -S appuser && adduser -S -G appuser appuser
COPY --from=builder /app/account/build/libs/account.jar app.jar

RUN chown appuser:appuser app.jar
USER appuser

ENV ACCOUNT_POSTGRES_USER=selfapp_user
ENV ACCOUNT_POSTGRES_PASSWORD=selfapp_password

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=3s --start-period=30s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
