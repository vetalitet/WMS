chcp 65001 >nul
@echo off
setlocal

:: Перехід до каталогу, де знаходиться цей скрипт
cd /d "%~dp0"

:: Виконання mvn install для модуля common-exceptions
echo Запуск mvn install для модуля common-exceptions...
cd common-exceptions
call mvn clean install
if %errorlevel% neq 0 (
    echo Помилка при виконанні mvn install для модуля common-exceptions. Завершення...
    exit /b 1
)
cd ..   && echo Повернення до кореневої директорії

:: Виконання docker-compose up для product-service
echo Запуск Docker Compose для product-service...
cd product-service
docker-compose up -d
if %errorlevel% neq 0 (
    echo Помилка при запуску Docker Compose для product-service.
    exit /b 1
)
cd ..   && echo Повернення до кореневої директорії

:: Виконання docker-compose up для inventory-service
echo Запуск Docker Compose для inventory-service...
cd inventory-service
docker-compose up -d
if %errorlevel% neq 0 (
    echo Помилка при запуску Docker Compose для inventory-service.
    exit /b 1
)

echo Модуль common-exceptions зібрано, product-service та inventory-service запущено.
