@echo off
if not exist "bin" mkdir bin
javac -d bin com/college/interfaces/*.java com/college/model/*.java com/college/util/*.java com/college/main/*.java
if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit /b %errorlevel%
)
echo Compilation successful.
