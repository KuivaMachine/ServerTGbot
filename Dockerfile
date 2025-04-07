
FROM openjdk:21-jdk-slim AS build

# Устанавливаем рабочую директорию
WORKDIR /servertgbot

# Копируем исходный код
COPY . .

# Собираем проект
RUN ./gradlew clean bootJar

# Финальный этап
FROM openjdk:21-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /servertgbot

# Копируем JAR-файл из этапа сборки
COPY build/libs/ServerTGbot-0.0.1.jar servertgbot.jar

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "servertgbot.jar"]