FROM payara/server-full:5.2021.10-jdk11
COPY target/javaee-loppis.war $DEPLOY_DIR