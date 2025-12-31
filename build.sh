#!/usr/bin/env bash
set -euo pipefail

# run from repo root (script directory)
cd "$(dirname "$0")"

# prefer wrapper if present
MVN="./mvnw"
if [ ! -x "$MVN" ]; then
    MVN="mvn"
fi

echo "Using $MVN"
echo "Building (skipping tests)..."
"$MVN" -q -DskipTests package
echo "Build completed."