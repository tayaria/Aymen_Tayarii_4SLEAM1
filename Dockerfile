# Étape 1 : Télécharge TOUT (dépendances + plugins Maven)
FROM maven:3.9.9-eclipse-temurin-17 AS deps
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
RUN mvn dependency:resolve-plugins -B   # ← LIGNE MAGIQUE qui télécharge les plugins manquants

# Étape 2 : Build du JAR en mode 100% offline
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
