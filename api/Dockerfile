# Use a imagem base do Gradle com JDK 17
FROM gradle:jdk17

# Defina o diret�rio de trabalho dentro do cont�iner
WORKDIR /app

# Copie os arquivos de configura��o e c�digo-fonte para o diret�rio de trabalho
COPY build.gradle .
COPY settings.gradle .
COPY src ./src

# Execute o comando Gradle para construir a aplica��o
RUN gradle build -x test

EXPOSE 8081

# Defina o comando de inicializa��o da aplica��o
CMD ["java", "-jar", "build/libs/api-backup-nota-0.0.1-SNAPSHOT.jar"]