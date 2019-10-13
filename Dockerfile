FROM jboss/wildfly

COPY build/libs/cacheExample.war /opt/jboss/wildfly/standalone/deployments/ 

ENV JAVA_OPTS="-XX:MaxMetaspaceSize=512m -Djava.net.preferIPv4Stack=true -Djava.net.preferIPv4Addresses=true"

EXPOSE 7800
EXPOSE 8080
EXPOSE 8081
EXPOSE 8443
EXPOSE 9990

USER root
	
RUN chown jboss:jboss /opt/jboss/wildfly/standalone/deployments/*
USER jboss