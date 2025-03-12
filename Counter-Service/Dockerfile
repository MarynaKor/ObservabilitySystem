FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar
COPY opentelemetry-javaagent.jar /opentelemetry-javaagent.jar
ENTRYPOINT ["java", \
             "-javaagent:/opentelemetry-javaagent.jar", \
             "-Dotel.javaagent.enabled=true", \
             "-Dotel.exporter.otlp.endpoint=http://otel-collector:4318", \
             "-jar", "/app.jar" \
           ]