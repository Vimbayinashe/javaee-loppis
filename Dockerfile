FROM payara/server-full:5.2021.10-jdk11
COPY target/javaee-loppis-1.0-SNAPSHOT.war $DEPLOY_DIR