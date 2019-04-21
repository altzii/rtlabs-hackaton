set -e

cd registry-service

./build-docker.sh

cd ../diagnosis-service

./build-docker.sh

cd ../gateway-service

./build-docker.sh

cd ../patients-service

./build-docker.sh

cd ../observation-service

./build-docker.sh

cd ../superhero-detection-service

./build-docker.sh

