# mvn clean package -DskipTests

# Use uma imagem base com JDK 17
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o arquivo JAR da aplicação para o contêiner
COPY target/food-ordermanager-0.0.1-SNAPSHOT.jar .

# Exponha a porta 8080
EXPOSE 8080

# Comando para rodar a aplicação Spring Boot
ENTRYPOINT ["java", "-jar", "food-ordermanager-0.0.1-SNAPSHOT.jar"]