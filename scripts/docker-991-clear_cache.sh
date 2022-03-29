#!/bin/bash

# Stop on error
set -e



# Load variables definitions
source variables.sh



echo "########################################"

DIR_CACHE_ANDROID=${DOCKER_DIR_CACHE}/android
echo "#    DIR_CACHE_ANDROID='${DIR_CACHE_ANDROID}'"

DIR_CACHE_GRADLE=${DOCKER_DIR_CACHE}/gradle
echo "#    DIR_CACHE_GRADLE='${DIR_CACHE_GRADLE}'"

echo "########################################"
echo ''
echo "########################################"
echo "+-- Delete cache..."

echo "    +-- DIR_CACHE_ANDROID..."
rm -rf ${DIR_CACHE_ANDROID}
echo "        +-- Done."

echo "    +-- DIR_CACHE_GRADLE..."
rm -rf ${DIR_CACHE_GRADLE}
echo "        +-- Done."

echo "########################################"
