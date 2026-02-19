chcp 65001 >nul
@echo off
setlocal

:: Перехід до каталогу, де знаходиться цей скрипт
cd /d "%~dp0"

:: ===============================
:: 1. Збірка common-exceptions
:: ===============================
echo Запуск mvn install для модуля common-exceptions...
cd common-exceptions
call mvn clean install
if %errorlevel% neq 0 (
    echo ❌ Помилка при виконанні mvn install для модуля common-exceptions.
    exit /b 1
)
cd ..
echo ✅ common-exceptions зібрано

:: ===============================
:: 2. Запуск product-service
:: ===============================
echo Запуск Docker Compose для product-service...
cd product-service
docker-compose up -d
if %errorlevel% neq 0 (
    echo ❌ Помилка при запуску Docker Compose для product-service.
    exit /b 1
)
cd ..
echo ✅ product-service запущено

:: ===============================
:: 3. Запуск inventory-service
:: ===============================
echo Запуск Docker Compose для inventory-service...
cd inventory-service
docker-compose up -d
if %errorlevel% neq 0 (
    echo ❌ Помилка при запуску Docker Compose для inventory-service.
    exit /b 1
)
cd ..
echo ✅ inventory-service запущено

:: ===============================
:: 4. Запуск facade-service
:: ===============================
echo Запуск Docker Compose для facade-service...
cd product-inventory-facade-service
docker-compose up -d
if %errorlevel% neq 0 (
    echo ❌ Помилка при запуску Docker Compose для product-inventory-facade-service.
    exit /b 1
)
cd ..
echo ✅ product-inventory-facade-service запущено

:: ===============================
:: 5. Очікування MySQL product-service
:: ===============================
echo Очікування готовності MySQL product-service...
:wait_product_db
docker exec my-mysql-product mysql -u root -proot -e "SELECT 1;" >nul 2>&1
if %errorlevel% neq 0 (
    timeout /t 2 >nul
    goto wait_product_db
)
echo ✅ MySQL product-service готовий!

:: ===============================
:: 6. Очікування MySQL inventory-service
:: ===============================
echo Очікування готовності MySQL inventory-service...
:wait_inventory_db
docker exec my-mysql-inventory mysql -u root -proot -e "SELECT 1;" >nul 2>&1
if %errorlevel% neq 0 (
    timeout /t 2 >nul
    goto wait_inventory_db
)
echo ✅ MySQL inventory-service готовий!

:: ===============================
:: 7. Очікування MySQL facade-service
:: ===============================
echo Очікування готовності MySQL facade-service...
:wait_facade_db
docker exec my-mysql-facade mysql -u root -proot -e "SELECT 1;" >nul 2>&1
if %errorlevel% neq 0 (
    timeout /t 2 >nul
    goto wait_facade_db
)
echo ✅ MySQL facade-service готовий!

:: ===============================
:: DONE
:: ===============================
echo.
echo 🎉 Усі сервіси та бази даних успішно запущені!
echo Product, Inventory та Facade готові до роботи.
echo.

endlocal
