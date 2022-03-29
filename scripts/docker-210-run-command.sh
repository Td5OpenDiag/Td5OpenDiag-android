#!/bin/bash

# Stop on error
set -e



##  @brief  Defines the command to be executed in the container - defaults to the Bash shell.
pCommand=${@:-bash}


# Load variables definitions
source variables.sh



echo "########################################"
#PROJECT_DIR_TOPLEVEL=../
echo "#    PROJECT_DIR_TOPLEVEL=${PROJECT_DIR_TOPLEVEL}"

DIR_CACHE_ANDROID=${DOCKER_DIR_CACHE}/android
echo "#    DIR_CACHE_ANDROID='${DIR_CACHE_ANDROID}'"

DIR_CACHE_GRADLE=${DOCKER_DIR_CACHE}/gradle
echo "#    DIR_CACHE_GRADLE='${DIR_CACHE_GRADLE}'"

echo "########################################"

#cp -r "${PROJECT_DIR_TOPLEVEL}" "${PROJECT_DIR_TMP}"


# ##################################################################################################
# Run the container
# ##################################################################################################

echo "########################################"
echo "+-- Starting container..."
echo "    +-- Image name: '${DOCKER_IMAGE_NAME}'"
echo "    +-- Command:    '${pCommand}'"
echo "########################################"


docker run \
    --privileged \
    --interactive \
    --tty \
    --rm \
    -v ${DIR_CACHE_ANDROID}:/root/.android \
    -v ${DIR_CACHE_GRADLE}:/root/.gradle/ \
    -v ${PROJECT_DIR_TOPLEVEL}:/workspace/project \
    ${DOCKER_IMAGE_NAME} \
    ${pCommand}

lExitCode=$?


echo "########################################"
echo "Container stopped: '${DOCKER_IMAGE_NAME}'."
echo "########################################"


exit ${lExitCode}
