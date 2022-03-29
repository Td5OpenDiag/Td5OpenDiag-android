#!/bin/bash

# Stop on error
set -e



# Load variables definitions
source variables.sh



# Build the image
docker build \
    --tag ${DOCKER_IMAGE_NAME} \
    ${PROJECT_DIR_TOPLEVEL}/docker/



exit $?
