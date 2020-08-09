Тестовое задание (backend)

Форма регистрации с отправкой имейла после одобрения из внешней системы.
 
Дана форма регистрации в нашем приложении, в которой необходимо заполнить:

- логин,
- пароль,
- адрес электронной почты,
- ФИО.

После отправки формы, мы регистрируем данные из нее в нашей БД, а также отправляем ее для одобрения во 
внешней системе. Пусть обмен с этой внешней системой будет через некое messaging решение. После одобрения 
или отклонения заявки, наше приложение должно отправить сообщение на электронную почту нашему пользователю 
с результатом проверки.

Стэк: JavaSE 8+, Spring boot 2, dbms - h2. Для тестов предпочтение Junit/Mockito/Assertj, т.к. на проекте 
будут именно они. Остальное по вкусу.

В качестве абстракции над шиной предлагаем взять такой набросок: 
https://pastebin.com/qWjRPuyp

Возвращать из примеров в наброске можно заглушки, дабы сэкономить время на реализацию тестового задания. 
Неплохо при этом помнить, что в реальной эксплуатации любая часть нашей системы может отказать. 
Будем очень рады обоснованиям принятых архитектурных решений. Комментарии в коде к ним крайне приветствуются.

**Логика рассуждений при разработке**

Пользователь отправляет форму с заполненными полями которые записываются в базу и отправляются в очередь 
с получением messageId от брокера. Т.к это относительно быстрые операции то они отрабатывают синхронно,
и отправляются пользователю с ответом об успехе или если произошла какой-либо эксепшн с общим сообщением об
ошибке(обработчик поставил один на все с обобщенным текстом и логированием в случае ошибки). Валидация ответа
и отправка письма сделал как ассинхронно действие так как посчитал что это относительно долгие события(в качестве
лиснера слушающего брокера является зацикленный шедулер(т.к. он работает в одном потоке то иногда задумывается
если попадет на событие sleep поэтому иногда минутку приходится подождать)).

**Запросы для тестирования**

GET http://localhost:8080/registration - получений view с формой
POST http://localhost:8080/registration - отправка данных из формы на обработку
GET http://localhost:8080/get-all-users - получение списка всех отправленных форм для просмотра