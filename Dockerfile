# ===== 1) STAGE de build: compila e gera o .jar =====
FROM gradle:jdk25 AS build
WORKDIR /app

# Copia tudo pro container de build
COPY . .

# Gera o jar (pula testes pra ser mais rápido)
RUN gradle clean bootJar -x test

# ===== 2) STAGE de runtime: só roda o jar (imagem mais leve) =====
FROM eclipse-temurin:25-jre
WORKDIR /app

# Copia o jar gerado no stage anterior
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
