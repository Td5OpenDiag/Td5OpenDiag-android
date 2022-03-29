#!/bin/bash

# Stop on error
# set -e


./210-run-command.sh \
    bash gradlew assembleDebug

exit $?
