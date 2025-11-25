# Nouvelle image officielle Java 17 (remplace openjdk:17-jdk-slim)
FROM eclipse-temurin:17-jdk-alpine

# Créer un utilisateur non-root (meilleure pratique)
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copier le JAR Spring Boot
COPY target/*.jar app.jar

# Exposer le port (8080 par défaut pour Spring Boot)
EXPOSE 8080

# Lancer l'application
ENTRYPOINT ["java", "-jar", "/app.jar"]
