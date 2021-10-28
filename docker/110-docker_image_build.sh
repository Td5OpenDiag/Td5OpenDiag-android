#!/bin/bash

set -e


DOCKER_IMAGE_NAME=test-android


# Build the image
docker build \
	--tag ${DOCKER_IMAGE_NAME} \
	.
	# --build-arg ANDROID_API_LEVEL=28 \
	# --build-arg ANDROID_BUILD_TOOLS_LEVEL=28.0.3 \
	# --build-arg GRADLE_VERSION=3.3 \
	# --build-arg EMULATOR_NAME=test \


exit $?
