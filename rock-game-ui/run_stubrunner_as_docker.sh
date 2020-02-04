#!/bin/bash

# Stop any running images
docker stop $(docker ps -a | grep spring-cloud-contract | awk '{print $1}') && echo "Killed a running container" || echo "Nothing running"

# Provide the Spring Cloud Contract Docker version
SC_CONTRACT_DOCKER_VERSION="2.1.0.BUILD-SNAPSHOT"
# Spring Cloud Contract Stub Runner properties
STUBRUNNER_PORT="8083"
# Stub coordinates 'groupId:artifactId:version:classifier:port'
STUBRUNNER_IDS="com.game:contracts-external:0.0.1-SNAPSHOT:stubs:9876"
# Run the docker with Stub Runner Boot
docker run  --rm -e "STUBRUNNER_IDS=${STUBRUNNER_IDS}" \
-e "STUBRUNNER_STUBS_MODE=LOCAL" \
-p "${STUBRUNNER_PORT}:${STUBRUNNER_PORT}" \
-p "9876:9876"  \
-v "${HOME}/.m2/:target/lib/" \
springcloud/spring-cloud-contract-stub-runner:"${SC_CONTRACT_DOCKER_VERSION}"
