FROM payara/server-full

ENV PAYARA_HTTP_PORT=8080

# Очистка кэша и старых развертываний
RUN rm -rf $DEPLOY_DIR/* \
    && rm -rf $DOMAIN_DIR/applications/* \
    && rm -rf $DOMAIN_DIR/generated/*

## Копирование файлов
#COPY business/build/libs/business-1.0-SNAPSHOT.jar $DEPLOY_DIR
COPY api/build/libs/api-1.0-SNAPSHOT.war $DEPLOY_DIR
COPY server-cert.crt /usr/local/share/ca-certificates/server-cert.crt




LABEL authors="Ri Arkadiy"

