#!/bin/bash

# set -e



PROJECT_DIR_TOPLEVEL=../

DOCKER_IMAGE_NAME=test-android

# Enter the top level of your project directory
# pushd ${PROJECT_DIR_TOPLEVEL}

PROJECT_DIR_TMP=`mktemp --directory /tmp/docker-android-build_XXX`

echo "########################################"
echo "#    PROJECT_DIR_TMP='${PROJECT_DIR_TMP}'"
echo "########################################"

cp -r "${PROJECT_DIR_TOPLEVEL}" "${PROJECT_DIR_TMP}"

DIR_CACHE=/tmp/${DOCKER_IMAGE_NAME}/cache
mkdir -p "${DIR_CACHE}"

DIR_CACHE_ANDROID=${DIR_CACHE}/android
DIR_CACHE_GRADLE=${DIR_CACHE}/gradle


# Run the container
docker run \
	--privileged \
	-it \
	--rm \
	-v ${PROJECT_DIR_TMP}:/workspace/project \
	${DOCKER_IMAGE_NAME} #\
	# -v ${DIR_CACHE_ANDROID}:/root/.android \
	# -v ${DIR_CACHE_GRADLE}:/root/.gradle/ \
	# -v $PWD:/workspace/project \
	# bash -c ". /workspace/emulator_start.sh && gradlew build -p /workspace/project"

exit_code=$?

# If no error occured, remove the temporary directory (otherwise keep it for debug)
if [[ "${exit_code}" -eq "0" ]]
then
	rm -rvf "${PROJECT_DIR_TMP}"
fi

exit ${exit_code}
