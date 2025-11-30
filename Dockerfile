# Étape 1 : Téléchargement complet des dépendances + résolution main class
FROM maven:3.9.9-eclipse-temurin-17 AS deps
WORKDIR /app
COPY pom.xml .
COPY src ./src
# On copie le src AVANT le package → Spring Boot trouve la @SpringBootApplication
RUN mvn -B dependency:go-offline dependency:resolve-plugins package -DskipTests

# Étape 2 : Build 100% offline (super rapide)
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY --from=deps /root/.m2 /root/.m2
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests -o

# Étape 3 : Image finale légère
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/student-management-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
