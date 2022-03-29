# ##################################################################################################
##  @file
##  @brief  This Dockerfile defines a Docker image containing a simple installation of the Android
##          SDK.
##
##  It is intended to be used as a controlled build/test environment for Android applications.
##
##  @par    References
##  + https://stackoverflow.com/a/34627928/1303262
##  + https://betterprogramming.pub/build-a-lightweight-docker-container-for-android-testing-2aa6bdaea422
##
##  @author Aloïké <aloike.go@gmail.com>
##
##  @date   2022-03-29 Tue.
##          Original release.
# ##################################################################################################

# Set the base image for subsequent instructions.
#FROM ubuntu:latest
FROM ubuntu:21.10



SHELL ["/bin/bash", "-c"]



# Fixup for tzdata data-enter request
ENV TZ=Europe/London
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone



# ##################################################################################################
#   Install packages from the official repositories
# ##################################################################################################

# Install Android SDK and some other tools from the distro's repositories
RUN     apt update \
    &&  apt install -y \
            android-sdk \
            git \
            vim \
            wget

# Set the environment variable that defines the Android SDK location
ENV ANDROID_SDK_ROOT=/usr/lib/android-sdk/



# ##################################################################################################
#   Install Android command line tools (especially `sdkmanager`)
# ##################################################################################################

# Download the release package,
# then Unzip it and copy it into the Android SDK location
RUN     cd /tmp \
    &&  wget https://dl.google.com/android/repository/commandlinetools-linux-8092744_latest.zip \
    &&  unzip commandlinetools-linux-8092744_latest.zip \
    &&  mkdir ${ANDROID_SDK_ROOT}/cmdline-tools \
    &&  cp -r cmdline-tools/ ${ANDROID_SDK_ROOT}/cmdline-tools/latest



# ##################################################################################################
#   Complementary steps
# ##################################################################################################

# Update SDK
RUN     yes Y | ${ANDROID_SDK_ROOT}/cmdline-tools/latest/bin/sdkmanager --update

# Install complementary SDK packages
COPY    rsrc/sdkmanager /workspace/rsrc_sdkmanager
RUN     cd /workspace/rsrc_sdkmanager \
    &&  bash sdkmanager_installPackagesFromList.sh

# Accept Android SDK licenses
RUN yes Y | ${ANDROID_SDK_ROOT}/cmdline-tools/latest/bin/sdkmanager --licenses

# Set the workdir to be the folder containing the project
WORKDIR /workspace/project





