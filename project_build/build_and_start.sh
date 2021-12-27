rm -rf postgres_data
cd ..

mvn clean package

cd project_build

docker-compose up --build