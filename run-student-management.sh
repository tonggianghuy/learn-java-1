#!/bin/bash

# Script to compile and run Student Management System

echo "=== Compiling Student Management System ==="
mkdir -p bin
javac -d bin src/main/java/com/learn/Student.java src/main/java/com/learn/StudentManagement.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo ""
    echo "=== Running Student Management System ==="
    echo ""
    java -cp bin com.learn.StudentManagement
else
    echo "Compilation failed!"
    exit 1
fi
