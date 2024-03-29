#!/bin/bash

MY_PATH=$(dirname "$0")            # relative
MY_PATH=$(cd "$MY_PATH" && pwd)    # absolutized and normalized
if [[ -z "$MY_PATH" ]] ; then
  # error; for some reason, the path is not accessible
  # to the script (e.g. permissions re-evaled after suid)
  exit 1  # fail
fi
echo "MY_PATH=${MY_PATH}"


##  @brief  Name of the Docker image used for building Android apps.
DOCKER_IMAGE_NAME=android-env


##  @brief Absolute path to the cache root directory.
##  The cache root directory groups various temporary directories used for speeding up the build.
DOCKER_DIR_CACHE=`echo "${MY_PATH}/../.cache_docker_tmp"`
mkdir -p "${DOCKER_DIR_CACHE}"
echo "#    DOCKER_DIR_CACHE='${DOCKER_DIR_CACHE}'"


##  @brief  Name of the default user to be created in the Docker image.
DOCKER_IMAGE_USERNAME_DEFAULT=user


##  @brief  Absolute path to the Project's root directory.
PROJECT_DIR_TOPLEVEL=`cd ${MY_PATH}/.. && pwd`
echo "#    PROJECT_DIR_TOPLEVEL='${PROJECT_DIR_TOPLEVEL}'"
