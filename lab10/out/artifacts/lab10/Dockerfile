FROM tomcat

COPY ./lab10.war /usr/local/tomcat/webapps
COPY ./mssql-jdbc-6.4.0.jre9.jar /usr/local/tomcat/lib/

ENV DB_URL=jdbc:sqlserver://172.17.0.2:1433;databaseName=SED
ENV DB_USER=sa
ENV DB_PASSWORD=Ks7631738

CMD ["catalina.sh", "run"]
