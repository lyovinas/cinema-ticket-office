#Образ взятый за основу
FROM openjdk:17-alpine
#Записываем в переменную путь до джарника (необязательно)
ARG jarFile=target/ticketoffice-0.0.1-SNAPSHOT.jar
#Куда переместить наш джарник внутри контейнера
WORKDIR /opt/app
#Копируем наш джарник в новый внутри контейнера
COPY ${jarFile} ticketoffice.jar
#Открываем порты
EXPOSE 8080
#Команда для запуска проекта
ENTRYPOINT ["java", "-jar", "ticketoffice.jar"]
