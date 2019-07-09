**_AuthenticationServletH2_**

Используемые модули и библиотеки для серверной части:
- Java Servlet API 4.0.1
- H2 Database Engine 1.4.199
- Apache FreeMarker 2.3.28
- Gretty 2.3.1

Используемые модули и библиотеки в клиентской части:
- Bootstrap 4.1.3
- JQuery 3.3.1
- Ajax 1.11.0

**_Краткое описание классов_**

**Утилитарные классы** (пакет *utils*):
  *  Класс **FreeM** - реализует работу с шаблонами FreeMarker
  *  Класс **EncoderPass** - реализует кодировку пароля
  *  Класс **ClientCookie** - реализует работу с куки
  *  Класс **ClientSession** - реализует работу с сессией

**Контроллеры** (пакет *controllers*):
  *  **Authentication** - страница авторизации
  *  **RegPage** - страница регистрации
  *  **UserPage** - страница информации о пользователе
  *  **Redirect** - страница переадресующая с корня сайта
  *  **Login** - скрипт авторизации
  *  **Registration** - скрипт регистрации
  *  **Logout** - скрипт выхода пользователя из сети

**Хранилище** (пакет *storage*):
  *  **ConnectionDataBase** - данный класс реализует синглтон для работы с бд.
  *  **PropReader** - данный класс нужен для получения данных по ключу из конфига.
  *  **StorageSingleton** - данный класс реализует синглтоны для работы с сущностями UserInfo, UserPass и UserToken.
  *  **userInfo** (пакет для сущности UserInfo): 
      *  **UserInfo** - сущность содержащая информацию о пользователе.
      *  **UserInfoDAO** - интерфейс для сущности UserInfo.
      *  **UserInfoDataBase** - реализация интерфейса UserInfoDAO для работы с бд.
      *  **UserInfoFactory** - фабрика для сущности UserInfo.
      *  **UserInfoTxtFile** - реализация интерфейса UserInfoDAO для работы с txt файлом.
  *  **userPass** (пакет для сущности UserPass):
      *  **UserPass** - сущность содержащая пароль пользователя.
      *  **UserPassDAO** - интерфейс для сущности UserPass.
      *  **UserPassDataBase** - реализация интерфейса UserPassDAO для работы с бд.
      *  **UserPassFactory** - фабрика для сущности UserPass.
      *  **UserPassTxtFile** - реализация интерфейса UserPassDAO для работы с txt файлом.
  *  **userToken** (пакет для сущности UserToken): 
      *  **UserToken** - сущность содержащая токен пользователя.
      *  **UserTokenAO** - интерфейс для сущности UserToken.
      *  **UserTokenDataBase** - реализация интерфейса UserTokenDAO для работы с бд.
      *  **UserTokenFactory** - фабрика для сущности UserToken.
      *  **UserTokenTxtFile** - реализация интерфейса UserTokenDAO для работы с txt файлом.        


**_Инструкция по запуску_**
1. Распаковать архив.
2. Изменить значения базы данных в `src/main/resources/config.properties` на необходимые.
3. Открыть консоль и ввести `gradlew build`.
4. Запустить базу данных.
5. Для запуска сервера ввести `gradlew appRun`.
6. Перейти по указанном адресу в браузере.
7. :+1:.
