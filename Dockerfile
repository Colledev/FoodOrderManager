# Usar uma imagem do OpenJDK como base
FROM openjdk:17-jdk-slim

# Instalar Maven
RUN apt-get update && apt-get install -y maven

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o conteúdo do projeto para o diretório de trabalho no container
COPY . .

# Compila o projeto usando Maven, ignorando os testes
RUN mvn clean package -DskipTests

# Expõe a porta que a aplicação vai rodar
EXPOSE 8080

# Comando para rodar a aplicação Spring Boot
ENTRYPOINT ["java", "-jar", "target/foodordermanager-0.0.1-SNAPSHOT.jar"]
