set -e

cd registry-service

./build-docker.sh

cd ../gateway-service

./build-docker.sh

cd ../registry-service

./build-docker.sh

cd ../observation-service

./build-docker.sh
