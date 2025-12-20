# Используем официальный образ с JDK 17
FROM eclipse-temurin:17-jdk-alpine

# Создаем рабочую директорию
WORKDIR /app

# Копируем исходники
COPY src ./src

# Компилируем Java-классы
RUN javac src/com/example/*.java

# Создаем папку для данных (будет использоваться как volume)
RUN mkdir -p data

# Команда для запуска
ENTRYPOINT ["java", "-cp", "src", "com.example.App"]