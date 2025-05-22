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

:: Очікування готовності product-service MySQL
echo Очікування готовності product-service MySQL...
:wait_product_db
docker exec my-mysql-product mysql -u root -proot -e "SELECT 1;" >nul 2>&1
if %errorlevel% neq 0 (
    timeout /t 2 >nul
    goto wait_product_db
)
echo ✅ MySQL product-service готовий!

:: Додавання початкових даних у product-service
echo Додавання початкових даних у product-service...
docker exec -i my-mysql-product mysql -u root -proot product-service-db < "%~dp0product-service/init-data.sql"
if %errorlevel% neq 0 (
    echo ❌ Помилка при додаванні даних у product-service-db
    exit /b 1
)