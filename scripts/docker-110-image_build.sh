#!/bin/bash

# Stop on error
set -e



# Load variables definitions
source variables.sh



# Build the image
docker build \
    --build-arg USER_NAME=${DOCKER_IMAGE_USERNAME_DEFAULT} \
    --tag ${DOCKER_IMAGE_NAME} \
    ${PROJECT_DIR_TOPLEVEL}/docker/



exit $?
