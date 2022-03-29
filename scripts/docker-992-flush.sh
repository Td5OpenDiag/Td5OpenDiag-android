#!/bin/bash

# Stop on error
set -e



# Load variables definitions
source variables.sh



#echo "########################################"

#DIR_CACHE_ANDROID=${DOCKER_DIR_CACHE}/android
#echo "#    DIR_CACHE_ANDROID='${DIR_CACHE_ANDROID}'"
#
#DIR_CACHE_GRADLE=${DOCKER_DIR_CACHE}/gradle
#echo "#    DIR_CACHE_GRADLE='${DIR_CACHE_GRADLE}'"

#echo "########################################"
#echo ''
echo "########################################"

echo "+-- Delete container image '${DOCKER_IMAGE_NAME}'..."
docker image rm ${DOCKER_IMAGE_NAME}

echo "########################################"
