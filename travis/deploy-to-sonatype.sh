mybatis_repo=$(git config --get remote.origin.url 2>&1)
if [ "$mybatis_repo" == "https://github.com/mybatis/hazelcast-cache.git" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then
  mvn deploy --settings ./travis/settings.xml
  echo -e "Successfully deployed SNAPSHOT artifacts to Sonatype under Travis job ${TRAVIS_JOB_NUMBER}"
fi