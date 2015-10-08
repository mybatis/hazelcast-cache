#!/bin/bash

# Get Project Repo
mybatis_repo=$(git config --get remote.origin.url 2>&1)
echo "Repo detected: ${mybatis_repo}"
 
# Get the Java version.
# Java 1.5 will give 15.
# Java 1.6 will give 16.
# Java 1.7 will give 17.
# Java 1.8 will give 18.
VER=`java -version 2>&1 | sed 's/java version "\(.*\)\.\(.*\)\..*"/\1\2/; 1q'`
echo "Java detected: ${VER}"

# We build for several JDKs on Travis.
# Some actions, like analyzing the code (Coveralls) and uploading
# artifacts on a Maven repository, should only be made for one version.
 
# If the version is 1.6, then perform the following actions.
# 1. Notify Coveralls.
# 2. Upload artifacts to Sonatype.
# 3. Use -q option to only display Maven errors and warnings.
# 4. Use --settings to force the usage of our "settings.xml" file.

if [ $VER == "16" ] && [ "$mybatis_repo" == "https://github.com/mybatis/hazelcast-cache.git" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then
  mvn clean deploy jacoco:report coveralls:report -q --settings ./travis/settings.xml
  echo -e "Successfully deployed SNAPSHOT artifacts to Sonatype and ran coveralls under Travis job ${TRAVIS_JOB_NUMBER}"
else
  echo "Travis build skipped"
fi