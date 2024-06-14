FROM liquibase/liquibase:4.9.1

ENV TZ="Europe/Moscow"

COPY src/main/resources/liquibase/ /liquibase/changelog/

CMD ["sh", "-c", "docker-entrypoint.sh --url=jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME} --username=${DB_USER} --password=${DB_PASS} --classpath='/liquibase/changelog' --changeLogFile=${LIQUI_CHANGELOG} --liquibase-schema-name=public --driver=org.postgresql.Driver update"]


