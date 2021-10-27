##  @see    https://betterprogramming.pub/build-a-lightweight-docker-container-for-android-testing-2aa6bdaea422

FROM ubuntu:latest


#-- # (Optional) To run docker as non-root, the simplest way is adding the current user to group docker
#-- 
#-- # Add group docker if it doesn't already exist
#-- RUN groupadd docker
#-- 
#-- # Add current user to group docker
#-- RUN gpasswd -a $USER docker 
#-- 
#-- # reload (or you can re-login to reload)
#-- RUN newgrp docker
#-- 
#-- # check if it works
#-- RUN docker run hello-world 


#
#   Install SDK Packages
#

RUN	apt update \
	&& 	apt install -y \
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

RUN	wget https://services.gradle.org/distributions/gradle-5.4.1-bin.zip -P /tmp \
	&&	unzip -d /opt/gradle /tmp/gradle-5.4.1-bin.zip

RUN	mkdir /opt/gradlew \
	&&	/opt/gradle/gradle-5.4.1/bin/gradle \
			wrapper \
			--gradle-version 5.4.1 \
			--distribution-type all \
			-p /opt/gradlew \
	&&	/opt/gradle/gradle-5.4.1/bin/gradle wrapper \
			-p /opt/gradlew

#   Android SDK
RUN	wget 'https://dl.google.com/android/repository/sdk-tools-linux-4333796.zip' \
		-P /tmp \
	&&	unzip \
			-d /opt/android \
			/tmp/sdk-tools-linux-4333796.zip 

RUN	yes Y | /opt/android/tools/bin/sdkmanager --install "platform-tools" "system-images;android-28;google_apis;x86" "platforms;android-28" "build-tools;28.0.3" "emulator"

# Accept all licences
RUN	yes Y | /opt/android/tools/bin/sdkmanager --licenses


# Now create an avd test:
RUN	echo "no" | /opt/android/tools/bin/avdmanager --verbose create avd --force --name "test" --device "pixel" --package "system-images;android-28;google_apis;x86" --tag "google_apis" --abi "x86"

# Check to see if it works!
RUN	/opt/android/emulator/emulator -list-avds



# ##############################################################################
#   3. Set up Environment Variables
# ##############################################################################

ENV GRADLE_HOME=/opt/gradle/gradle-5.4.1
ENV ANDROID_HOME=/opt/android
ENV PATH=$PATH:$GRADLE_HOME/bin:/opt/gradlew:$ANDROID_HOME/emulator:$ANDROID_HOME/tools/bin:$ANDROID_HOME/platform-tools
ENV LD_LIBRARY_PATH= $ANDROID_HOME/emulator/lib64:$ANDROID_HOME/emulator/lib64/qt/lib



# ##############################################################################
#   4. Run Emulator
# ##############################################################################

ENV     DIR_WORKSPACE=/workspace

COPY    rsrc/emulator_start.sh  ${DIR_WORKSPACE}

WORKDIR ${DIR_WORKSPACE}
RUN     bash emulator_start.sh


