#!/usr/bin/env bash
set -euo pipefail

echo "========================================"
echo " Running unit tests for CLI project"
echo "========================================"

# Ensure we're in the project root (where pom.xml lives)
if [[ ! -f "pom.xml" ]]; then
  echo "ERROR: pom.xml not found. Run this script from the project root."
  exit 1
fi

echo "Cleaning project..."
mvn clean

echo "Running unit tests..."
mvn test

echo "----------------------------------------"
echo "All tests completed successfully."
echo "----------------------------------------"
