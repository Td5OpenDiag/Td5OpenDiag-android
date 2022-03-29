#!/bin/bash

set -e


FILE_PACKAGES_LIST='sdkmanager_packages.txt'



while read line
do
    # Deal with comment and empty lines
    line=`echo "$line"|sed -e 's@#.*$@@'`
    if [[ -z "$line" ]]
    then
        continue
    fi

    echo $line
    yes Y | /usr/lib/android-sdk/cmdline-tools/latest/bin/sdkmanager --install "${line}"

done <<< $(cat "${FILE_PACKAGES_LIST}")


exit $?
