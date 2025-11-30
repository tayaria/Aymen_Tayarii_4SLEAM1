# === Étape 1 : Téléchargement de TOUTES les dépendances (c’est ÇA qui évite Connection reset) ===
FROM maven:3.9.9-eclipse-temurin-17 AS dependencies
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B

# === Étape 2 : Build du projet ===
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY --from=dependencies /root/.m2 /root/.m2
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests -o   # -o = offline → plus jamais de téléchargement

# === Étape 3 : Image finale légère ===
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/student-management-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
