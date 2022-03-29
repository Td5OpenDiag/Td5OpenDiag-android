#!/bin/bash



# Load variables definitions
source variables.sh



# FILE_APK=${FILE_APK:-${PROJECT_DIR_TOPLEVEL}/app/build/outputs/apk/debug/app-debug.apk}
APK_DIRPATH=${APK_DIRPATH:-${PROJECT_DIR_TOPLEVEL}/app/build/outputs/apk/debug/}
APK_FILENAME=${APK_FILENAME:-app-debug.apk}



adb -d install ${APK_DIRPATH}/${APK_FILENAME}

exit $?
