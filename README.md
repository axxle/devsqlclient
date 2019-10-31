# devsqlclient
Simple SQL client for developers

Основные настройки прописаны тут: /src/main/resources/application.properties
некоторые значения ссылаются на системные переменные, пример под windows в /set_system_env_template.bat, просмотр текущих значений echo_system_env.bat
Прописать значения для доступа к БД
Положить jdbc-драйвер в директорию /jdbcdrivers, при необходимости поправить в build.gradle: dependencies {...compile files('jdbcdrivers/ojdbc6.jar'...)}

Запуск - ./gradlew build && java -jar build/libs/devsqlclient-0.1.0.jar