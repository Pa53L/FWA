FROM ubuntu:latest
RUN apt-get -y update && apt-get -y upgrade
RUN apt-get -y install openjdk-8-jdk wget
RUN mkdir /usr/local/tomcat
RUN wget https://dlcdn.apache.org/tomcat/tomcat-8/v8.5.73/bin/apache-tomcat-8.5.73.tar.gz -O /tmp/tomcat.tar.gz
RUN cd /tmp && tar xvfz tomcat.tar.gz
RUN cp -Rv /tmp/apache-tomcat-8.5.73/* /usr/local/tomcat/
RUN apt-get install -y curl
#CMD /usr/local/tomcat/bin/catalina.sh run
#ADD "target/cinema.war" /usr/local/tomcat/webapps/cinema.war
COPY --chmod=0755 ./entrypoint.sh /usr/bin/
ENTRYPOINT ["entrypoint.sh"]
CMD ["echo", " DB is available "]
CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]