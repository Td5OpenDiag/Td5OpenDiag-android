#!/bin/bash

# Stop on error
# set -e


./docker-210-run-command.sh \
    bash gradlew assembleDebug

exit $?
