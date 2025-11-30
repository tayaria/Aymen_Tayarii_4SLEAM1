# Étape 1 : On télécharge ABSOLUMENT TOUT en ONLINE (1 seule fois)
FROM maven:3.9.9-eclipse-temurin-17 AS deps
WORKDIR /app
COPY pom.xml .
# Cette commande magique télécharge TOUTES les dépendances + plugins + BOM Spring Boot
RUN mvn -B dependency:go-offline dependency:resolve-plugins verify --fail-never

# Étape 2 : Build 100 % offline (plus jamais de réseau)
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
