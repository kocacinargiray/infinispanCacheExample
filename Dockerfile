FROM tomcat:8.0.20-jre8
VOLUME /tmp
COPY build/libs/cacheExample.war /usr/local/tomcat/webapps/cacheExample.war
EXPOSE 8080
CMD ["catalina.sh", "run"]

