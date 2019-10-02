FROM jboss/wildfly

ADD build/libs/cacheExample.war /opt/jboss/wildfly/standalone/deployments/ 

ENV JAVA_OPTS="-Djava.net.preferIPv4Stack=true -Djava.net.preferIPv4Addresses=true"

EXPOSE 8080
EXPOSE 7800
EXPOSE 9990
EXPOSE 8888

USER root
	
RUN chown jboss:jboss /opt/jboss/wildfly/standalone/deployments/*
USER jboss