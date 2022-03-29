#!/bin/bash

set -e


# # ------------------------------------------------------------------------------
# # Start emulator
# # ------------------------------------------------------------------------------
# echo -e "\n\n"
# echo "########################################"
# echo "#    Start the emulator"
# echo "########################################"
# source ${DIR_WORKSPACE}/emulator_start.sh


# ------------------------------------------------------------------------------
# Build the project
# ------------------------------------------------------------------------------
echo "########################################"
echo "#    Build the project"
echo "########################################"

gradlew build \
	-p ${DIR_PROJECT} \
	--stacktrace
	# --warning-mode all      #< to show the individual deprecation warnings.
	# --scan  \   #< to get full insights.


exit $?
