#!/usr/bin/env bash
set -euo pipefail

cd "$(dirname "$0")"

MVN="./mvnw"
if [ ! -x "$MVN" ]; then
    MVN="mvn"
fi

echo "Using $MVN"

# Initialize to avoid "unbound variable" when 'set -u' is in effect
EXTRA_ARGS=""

if [ "$#" -gt 0 ]; then
    EXTRA_ARGS="-Dexec.args=$*"
fi

echo "Running cli.Main..."
echo "Installing parent POM..."
"$MVN" -q -DskipTests -N install

echo "Building cli-framework..."
"$MVN" -q -DskipTests -pl cli-framework install

echo "Running cli.Main..."
"$MVN" -q -DskipTests -pl cli-demo package
"$MVN" -q -DskipTests -pl cli-demo exec:java $EXTRA_ARGS
