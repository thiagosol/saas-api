## Stage 1 : build with maven builder image with native capabilities
FROM quay.io/quarkus/ubi-quarkus-mandrel-builder-image:jdk-21 AS build
COPY --chown=quarkus:quarkus --chmod=0755 mvnw /app/mvnw
COPY --chown=quarkus:quarkus .mvn /app/.mvn
COPY --chown=quarkus:quarkus pom.xml /app/

USER quarkus
WORKDIR /app
#RUN ./mvnw -B org.apache.maven.plugins:maven-dependency-plugin:3.8.1:go-offline
COPY src /app/src
RUN ./mvnw package -Dnative -DskipTests

## Stage 2 : create the docker final image
FROM quay.io/quarkus/quarkus-micro-image:2.0
WORKDIR /app
COPY --from=build /app/target/*-runner /app/application

# set up permissions for user `1001`
RUN chmod 775 /app /app/application \
  && chown -R 1001 /app \
  && chmod -R "g+rwX" /app \
  && chown -R 1001:root /app

EXPOSE 8080
USER 1001

CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]