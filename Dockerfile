# Tahap 1: Build aplikasi Spring Boot
FROM maven:3.8.3-jdk-11 AS build

# Buat direktori untuk aplikasi
WORKDIR /app

# Salin file pom.xml terlebih dahulu agar dependensi dapat di-cache
COPY pom.xml .

# Unduh dan cache dependensi aplikasi
RUN mvn dependency:go-offline -B

# Salin kode aplikasi
COPY src ./src

# Build aplikasi dengan Maven
RUN mvn package -DskipTests

# Tahap 2: Buat image runtime dengan JRE slim
FROM openjdk:11-jre-slim

# Buat direktori untuk aplikasi
WORKDIR /app

# Salin file JAR aplikasi dari tahap build sebelumnya ke dalam image runtime
COPY --from=build /app/target/business-capital-loan-0.0.1-SNAPSHOT.jar /app/business-capital-loan-0.0.1-SNAPSHOT.jar

# Eksekusi perintah ketika container berjalan
CMD ["java", "-jar", "/app/business-capital-loan-0.0.1-SNAPSHOT.jar"]