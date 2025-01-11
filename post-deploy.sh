#!/bin/bash

# Ждем полной загрузки сервера
sleep 5

# Развертываем сначала business.jar
asadmin deploy /opt/payara/deployments/business-1.0-SNAPSHOT.jar

# Ждем завершения первого развертывания
sleep 5

# Затем развертываем api.war
asadmin deploy /opt/payara/deployments/api-1.0-SNAPSHOT.war

# Держим контейнер запущенным
/opt/payara/scripts/startInForeground.sh