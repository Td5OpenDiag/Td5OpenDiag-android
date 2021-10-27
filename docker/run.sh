#!/bin/bash

set -e


DOCKER_IMAGE_NAME=test-android


# Build the image
docker build \
	--tag ${DOCKER_IMAGE_NAME} \
	.

# Run the container from the image

