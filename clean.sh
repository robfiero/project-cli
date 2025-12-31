#!/usr/bin/env bash
set -euo pipefail

cd "$(dirname "$0")"

MVN="./mvnw"
if [ ! -x "$MVN" ]; then
    MVN="mvn"
fi

echo "Using $MVN"
echo "Cleaning..."
"$MVN" -q clean
echo "Clean completed."