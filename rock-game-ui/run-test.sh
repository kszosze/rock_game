#!/bin/bash

set -o errexit

WAIT_TIME="${WAIT_TIME:-10}"


# Run Stub Runner
echo "Running Stub Runner Docker Image"
# nohup ./run_stubrunner_as_docker.sh &
nohup ./run_stubrunner_as_process.sh &

echo "Waiting for the image to start for [${WAIT_TIME}] seconds"
sleep ${WAIT_TIME}

# Execute tests
echo "Working around certificate issues" && npm config set strict-ssl false
yes | npm install || echo "Failed to install packages"
npm run start-test

# Stop any running images
docker stop $(docker ps -a | grep spring-cloud-contract | awk '{print $1}') && echo "Killed a running container" || echo "Nothing running"
