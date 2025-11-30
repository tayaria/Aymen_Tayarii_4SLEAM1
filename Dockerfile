# Étape 1 : Téléchargement complet des dépendances (ONLINE, sans --fail-never
FROM maven:3.9.9-eclipse-temurin-17 AS deps
WORKDIR /app
COPY pom.xml .
# On force le téléchargement de TOUT sans ignorer les erreurs
RUN mvn -B dependency:go-offline dependency:resolve-plugins package -DskipTests

# Étape 2 : Build 100% offline
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY --from=deps /root/.m2 /root/.m2
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests -o

# Étape 3 : Image finale
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/student-management-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
