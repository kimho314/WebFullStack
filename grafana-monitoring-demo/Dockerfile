# ---------- Build Stage ----------
FROM curlimages/curl:8.13.0 AS download
ARG OTEL_AGENT_VERSION="2.29.0"
RUN curl --silent --show-error --fail --insecure -L "https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v${OTEL_AGENT_VERSION}/opentelemetry-javaagent.jar" \
    -o "$HOME/opentelemetry-javaagent.jar"

FROM gradle:9.5.1-jdk25 AS builder

WORKDIR /build

COPY build.gradle settings.gradle ./
COPY gradle ./gradle

RUN gradle dependencies --no-daemon

COPY . .

RUN gradle bootJar --no-daemon


# ---------- Runtime Stage ----------


FROM eclipse-temurin:25-jre-jammy

WORKDIR /app

# create non-root user
RUN useradd -ms /bin/bash springuser

COPY --from=builder /build/build/libs/*.jar app.jar

COPY --from=download --chown=springuser:springuser /home/curl_user/opentelemetry-javaagent.jar /opentelemetry-javaagent.jar

# create public directory and set permissions
RUN mkdir -p /app/public \
    && chown -R springuser:springuser /app \
    && chmod -R 755 /app/public

# security
RUN chown springuser:springuser app.jar
USER springuser

EXPOSE 8080

# JVM tuning for container
ENTRYPOINT ["java", \
"-XX:MaxRAMPercentage=80", \
"-XX:+UseG1GC", \
"-Dspring.profiles.active=prod", \
"-javaagent:/opentelemetry-javaagent.jar", \
"-jar", \
"/app/app.jar"]