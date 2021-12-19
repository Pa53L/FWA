rm -rf postgres_data
cd ..

if [[ $1 == "build" ]]; then
  mvn clean package
fi

cd project_build

docker-compose up --build