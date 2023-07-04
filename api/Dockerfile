# Use a imagem base do Gradle com JDK 17
FROM gradle:jdk17

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie os arquivos de configuração e código-fonte para o diretório de trabalho
COPY build.gradle .
COPY settings.gradle .
COPY src ./src

# Execute o comando Gradle para construir a aplicação
RUN gradle build -x test

EXPOSE 8081

# Defina o comando de inicialização da aplicação
CMD ["java", "-jar", "build/libs/api-backup-nota-0.0.1-SNAPSHOT.jar"]