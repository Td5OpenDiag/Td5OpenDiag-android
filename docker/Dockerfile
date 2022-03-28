##  @see    https://betterprogramming.pub/build-a-lightweight-docker-container-for-android-testing-2aa6bdaea422

FROM ubuntu:latest



SHELL ["/bin/bash", "-c"]


# Fixup for tzdata data-enter request
ENV TZ=Europe/London
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone


# ##############################################################################
#   Install SDK Packages
# ##############################################################################

#   APT dependencies

RUN     apt update \
    &&  apt install -y \
            git \
            libasound2 \
            libc6 \
            libglu1 \
            libnss3 \
            libpulse-dev \
            libstdc++6 \
            libx11-6 \
            libx11-xcb1 \
            libxcb1 \
            libxcomposite1 \
            libxcursor1 \
            libxi6 \
            libxtst6 \
            openjdk-8-jdk \
            unzip \
            vim \
            wget


#   Gradle
ARG GRADLE_VERSION=6.9.1

RUN     wget https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -P /tmp \
    &&  unzip -d /opt/gradle /tmp/gradle-${GRADLE_VERSION}-bin.zip \
    &&  mkdir /opt/gradlew \
    &&  /opt/gradle/gradle-${GRADLE_VERSION}/bin/gradle wrapper --gradle-version ${GRADLE_VERSION} --distribution-type all -p /opt/gradlew  \
    &&  /opt/gradle/gradle-${GRADLE_VERSION}/bin/gradle wrapper -p /opt/gradlew

ENV GRADLE_HOME=/opt/gradle/gradle-$GRADLE_VERSION


#   Android SDK
ARG ANDROID_API_LEVEL=28
ARG ANDROID_BUILD_TOOLS_LEVEL=28.0.3

RUN     wget 'https://dl.google.com/android/repository/sdk-tools-linux-4333796.zip' -P /tmp \
    &&  unzip -d /opt/android /tmp/sdk-tools-linux-4333796.zip \
    &&  yes Y | /opt/android/tools/bin/sdkmanager --install "platform-tools" "system-images;android-${ANDROID_API_LEVEL};google_apis;x86" "platforms;android-${ANDROID_API_LEVEL}" "build-tools;${ANDROID_BUILD_TOOLS_LEVEL}" "emulator" \
    &&  yes Y | /opt/android/tools/bin/sdkmanager --licenses \
    &&  echo "no" | /opt/android/tools/bin/avdmanager --verbose create avd --force --name "test" --device "pixel" --package "system-images;android-${ANDROID_API_LEVEL};google_apis;x86" --tag "google_apis" --abi "x86"

ENV ANDROID_HOME=/opt/android

ENV PATH "$PATH:$GRADLE_HOME/bin:/opt/gradlew:$ANDROID_HOME/emulator:$ANDROID_HOME/tools/bin:$ANDROID_HOME/platform-tools"
ENV LD_LIBRARY_PATH "$ANDROID_HOME/emulator/lib64:$ANDROID_HOME/emulator/lib64/qt/lib"






# ##############################################################################
#   4. Run Emulator
# ##############################################################################

ENV     DIR_WORKSPACE=/workspace
ENV     DIR_PROJECT=${DIR_WORKSPACE}/project

# Set the working directory
RUN     mkdir -p ${DIR_WORKSPACE}
WORKDIR ${DIR_WORKSPACE}

# Add utility scripts
COPY    rsrc/emulator_start.sh  ${DIR_WORKSPACE}

# Add entry point
COPY    rsrc/entrypoint.sh  ${DIR_WORKSPACE}
RUN     chmod +x ${DIR_WORKSPACE}/entrypoint.sh
ENTRYPOINT [ "/workspace/entrypoint.sh" ]





# # ##############################################################################
# #   3. Set up Environment Variables
# # ##############################################################################

# ENV GRADLE_HOME=/opt/gradle/gradle-5.4.1
# ENV ANDROID_HOME=/opt/android
# ENV PATH=$PATH:$GRADLE_HOME/bin:/opt/gradlew:$ANDROID_HOME/emulator:$ANDROID_HOME/tools/bin:$ANDROID_HOME/platform-tools
# ENV LD_LIBRARY_PATH= $ANDROID_HOME/emulator/lib64:$ANDROID_HOME/emulator/lib64/qt/lib






