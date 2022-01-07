rm -rf postgres_data
cd ..

if [[ $1 == "build" ]]; then
  mvn clean package
fi
docker network create cinemanetwork
echo "Sleeping 20 seconds"
sleep 20
echo "Completed"
cd project_build
docker-compose up --build --detach
echo "Sleeping 40 seconds"
sleep 40
echo "Completed"
cd ..
docker build -t cinema .
docker run -p 127.0.0.1:8082:8080/tcp --name=web --network=cinemanetwork -d cinema
