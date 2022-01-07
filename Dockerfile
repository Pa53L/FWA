FROM tomcat:9.0.56-jre8-temurin-focal
COPY target/cinema.war /usr/local/tomcat/webapps/cinema.war
EXPOSE 8080
#ENV POSTGRES_DB="fwa"
#ENV POSTGRES_USER="fwa"
#ENV POSTGRES_PASSWORD="password"
#ENV POSTGRES_URL="jdbc:postgresql://db:5432/fwa"