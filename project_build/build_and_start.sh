cd ..

if [[ $1 == "build" ]]; then
  mvn clean package
fi

cd project_build
rm -rf postgres_data

docker-compose up --build