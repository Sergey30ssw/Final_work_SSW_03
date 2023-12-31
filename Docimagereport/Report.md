# Отчет о результатах тестирования

Автоматизированное тестирование сервиса «Путешествие дня» для приобретения тура с оплатой онлайн и оплатой в кредит завершено в полном объёме.

# Тестовое окружение
- **Операционная система:** Windows 10 Pro v. 21H2
- **IDE:** IntelliJ IDEA 2023.1.2 (Community Edition)
- **JAVA:** OpenJDK 11
- **Docker Desktop:** version 4.24.1 (123237)
- **Google Chrome:** version  119.0.6045.160 (Official Build, 32-bit)

# Результаты тестирования
Реализовано 60 автотестов, из них успешных 28, что составляет 46,66% от общего числа.
Визуализация отчетов Allure по результатам прохождения тестирования приведена на скриншотах №№1÷8.

![image](https://github.com/Sergey30ssw/Final_work_SSW_03/assets/133129423/04449fab-82af-4554-87ba-c1ac139669fa)

**Скриншот №1. Общая кольцевая диаграмма, распределение тестов по категориям и успешности.**

![image](https://github.com/Sergey30ssw/Final_work_SSW_03/assets/133129423/93f79e51-0751-4e7c-ba95-6163db4e4623)
![image](https://github.com/Sergey30ssw/Final_work_SSW_03/assets/133129423/08dc1bb6-ba9d-4678-aef9-cb7dec889661)
![image](https://github.com/Sergey30ssw/Final_work_SSW_03/assets/133129423/c349b8d3-3f21-4830-90a8-3a6b11c77ff4)

**Скриншоты №№2, 3, 4. Описание тестов с выявленными дефектами (багами).**

![image](https://github.com/Sergey30ssw/Final_work_SSW_03/assets/133129423/e6b66499-e642-486b-abd9-db6a97898836)
![image](https://github.com/Sergey30ssw/Final_work_SSW_03/assets/133129423/d44bb8c1-a0cd-4fce-b401-8c9b33cec2f4)
![image](https://github.com/Sergey30ssw/Final_work_SSW_03/assets/133129423/7e484b65-33ce-4bd7-8e8d-8aa52604f1ca)

**Скриншоты №№5, 6, 7. Общий список автотестов с таймингом.**

![image](https://github.com/Sergey30ssw/Final_work_SSW_03/assets/133129423/e5023358-5d68-48cc-9e9d-2ba6f1ab0942)

**Скриншот №8. Продолжительность автотестов по времени.**

Примечание: ось абсцисс – время в секундах, ось ординат – количество автотестов.

# Реализованные задачи

1) Составлен [план](https://github.com/Sergey30ssw/Final_work_SSW_03/blob/main/Docimagereport/Automatization_plan.md) автоматизации тестирования;

2) Настроена и запущена тестовая система с использованием Data Bases MySQL и PostgreSQL;

3) Проведена автоматизация 60 [сценариев](https://github.com/Sergey30ssw/Final_work_SSW_03/blob/main/Docimagereport/Automatization_plan.md#2-%D0%BF%D0%B5%D1%80%D0%B5%D1%87%D0%B5%D0%BD%D1%8C-%D0%B0%D0%B2%D1%82%D0%BE%D0%BC%D0%B0%D1%82%D0%B8%D0%B7%D0%B8%D1%80%D1%83%D0%B5%D0%BC%D1%8B%D1%85-%D1%81%D1%86%D0%B5%D0%BD%D0%B0%D1%80%D0%B8%D0%B5%D0%B2) (18 позитивных и 42 негативных сценария) для тестирования API и UI;

4) Оформлено в виде [ISSUES](https://github.com/Sergey30ssw/Final_work_SSW_03/issues) 32 отчета о дефектах (баг-репорта);

5) Составлен данный отчет.

# Риски, возникшие при работе над реализацией проекта
Из перечня рисков, описанных в [разделе 3 плана автоматизации](https://github.com/Sergey30ssw/Final_work_SSW_03/blob/main/Docimagereport/Automatization_plan.md#3-%D0%BF%D0%B5%D1%80%D0%B5%D1%87%D0%B5%D0%BD%D1%8C-%D0%B2%D0%BE%D0%B7%D0%BC%D0%BE%D0%B6%D0%BD%D1%8B%D1%85-%D1%80%D0%B8%D1%81%D0%BA%D0%BE%D0%B2-%D0%BF%D1%80%D0%B8-%D0%B0%D0%B2%D1%82%D0%BE%D0%BC%D0%B0%D1%82%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D0%B8), рисков не возникло. Проект продлился на месяц вопреки ожиданиям ввиду возникновения обстоятельств, с его реализацией не связанных, а именно, - увеличением нагрузки по основной работе студента.

# Общий итог работы над проектом
На создание нового проекта в IDEA, настройку и подготовку окружения [в разделе 4 плана автоматизации](https://github.com/Sergey30ssw/Final_work_SSW_03/blob/main/Docimagereport/Automatization_plan.md#4-%D0%B8%D0%BD%D1%82%D0%B5%D1%80%D0%B2%D0%B0%D0%BB%D1%8C%D0%BD%D0%B0%D1%8F-%D0%BE%D1%86%D0%B5%D0%BD%D0%BA%D0%B0-%D1%81-%D1%83%D1%87%D1%91%D1%82%D0%BE%D0%BC-%D1%80%D0%B8%D1%81%D0%BA%D0%BE%D0%B2) было отведено 10 часов; фактическое время составило 8 часов.

На создание, запуск и проверку автотестов было [отведено](https://github.com/Sergey30ssw/Final_work_SSW_03/blob/main/Docimagereport/Automatization_plan.md#4-%D0%B8%D0%BD%D1%82%D0%B5%D1%80%D0%B2%D0%B0%D0%BB%D1%8C%D0%BD%D0%B0%D1%8F-%D0%BE%D1%86%D0%B5%D0%BD%D0%BA%D0%B0-%D1%81-%D1%83%D1%87%D1%91%D1%82%D0%BE%D0%BC-%D1%80%D0%B8%D1%81%D0%BA%D0%BE%D0%B2) 36 часов; фактически затрачено 42 часа в связи с возникшей по ходу реализации проекта необходимостью повторного прогона автотестов в домашних условиях с высокоскоростным Интернет-соединением.

На подготовку отчетной документации (включая ISSUE) было [отведено](https://github.com/Sergey30ssw/Final_work_SSW_03/blob/main/Docimagereport/Automatization_plan.md#4-%D0%B8%D0%BD%D1%82%D0%B5%D1%80%D0%B2%D0%B0%D0%BB%D1%8C%D0%BD%D0%B0%D1%8F-%D0%BE%D1%86%D0%B5%D0%BD%D0%BA%D0%B0-%D1%81-%D1%83%D1%87%D1%91%D1%82%D0%BE%D0%BC-%D1%80%D0%B8%D1%81%D0%BA%D0%BE%D0%B2) 10 часов; затрачено 18 часов в связи с возникшей по ходу реализации проекта необходимостью повторного прогона автотестов в домашних условиях с высокоскоростным Интернет-соединением и компоновкой скриншотов в отдельных файлах для дальнейшего перемещения в удалённый репозиторий.

На работу с локальным и удаленным репозиториями для размещения проекта было [отведено](https://github.com/Sergey30ssw/Final_work_SSW_03/blob/main/Docimagereport/Automatization_plan.md#4-%D0%B8%D0%BD%D1%82%D0%B5%D1%80%D0%B2%D0%B0%D0%BB%D1%8C%D0%BD%D0%B0%D1%8F-%D0%BE%D1%86%D0%B5%D0%BD%D0%BA%D0%B0-%D1%81-%D1%83%D1%87%D1%91%D1%82%D0%BE%D0%BC-%D1%80%D0%B8%D1%81%D0%BA%D0%BE%D0%B2) 6 часов; фактически затрачено 6 часов, включая время на размещение ISSUE.

На устранение замечаний было [отведено](https://github.com/Sergey30ssw/Final_work_SSW_03/blob/main/Docimagereport/Automatization_plan.md#4-%D0%B8%D0%BD%D1%82%D0%B5%D1%80%D0%B2%D0%B0%D0%BB%D1%8C%D0%BD%D0%B0%D1%8F-%D0%BE%D1%86%D0%B5%D0%BD%D0%BA%D0%B0-%D1%81-%D1%83%D1%87%D1%91%D1%82%D0%BE%D0%BC-%D1%80%D0%B8%D1%81%D0%BA%D0%BE%D0%B2) 6 часов; фактически затрачено 5 часов.






