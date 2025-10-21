#!/bin/bash
# EduCloud GUI Runner Script
# Compiles and runs the EduCloud Java GUI App

# Stop on errors
set -e

# Define directories
SRC_DIR="src"
BIN_DIR="bin"

# Create bin directory if missing
mkdir -p "$BIN_DIR"

find "$SRC_DIR" -name "*.java" >sources.txt
javac -d "$BIN_DIR" @sources.txt
rm sources.txt

java -cp "$BIN_DIR" EduCloudApp
