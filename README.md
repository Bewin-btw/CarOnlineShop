CarShop_NYC: Онлайн-магазин автомобилей

CarShop_NYC — это проект по разработке бэкенд-части онлайн-магазина автомобилей, который включает функции управления каталогом, корзиной, скидками и оплатой. Проект разработан в рамках курса по паттернам проектирования и демонстрирует использование 12 различных паттернов проектирования.

Команда разработки
Erik Aktaev
Alana Mukanova
Aruzhan Amanbekova
Описание
CarShop_NYC представляет собой онлайн-магазин автомобилей, реализованный в виде бэкенд-системы с поддержкой интерфейса командной строки. Система поддерживает следующие основные функции:

Каталог автомобилей с разделением по маркам и моделям.
Корзина для добавления и удаления автомобилей перед покупкой.
Различные стратегии ценообразования, включая VIP и сезонные скидки.
Возможность добавления опций к автомобилям (например, люк на крыше и спортивный пакет).
Различные методы оплаты (PayPal, Credit Card).
Уведомления о доступности автомобилей и управление состоянием автомобиля (доступен, зарезервирован, продан).
Технологии и паттерны проектирования
Проект использует Java и демонстрирует 12 паттернов проектирования, разделенных на три категории: порождающие, структурные и поведенческие.

Порождающие паттерны

Singleton: Обеспечивает единственный экземпляр InventoryService для централизованного управления инвентарем.
Factory: Используется для создания автомобилей различных марок через CarFactory.
Builder: Позволяет настраивать автомобиль с добавлением опций, таких как Sunroof и Sport Package.
Структурные паттерны

Composite: Организует каталог автомобилей, представляя каждую марку как группу автомобилей.
Decorator: Добавляет дополнительные опции к автомобилям, используя SunroofDecorator и SportPackageDecorator.
Facade: Упрощает доступ к основным функциям магазина через класс CarShopFacade.
Adapter: Интегрирует разные методы оплаты (PayPal, Credit Card) с помощью PaymentAdapter.
Поведенческие паттерны

Strategy: Поддерживает разные стратегии ценообразования для различных типов клиентов.
Observer: Обеспечивает уведомления для пользователей о наличии автомобилей через InventoryNotifier.
Command: Управляет добавлением автомобилей в корзину через AddToCartCommand.
Chain of Responsibility: Обрабатывает цепочку скидок на автомобили, включая VIP, сезонные и скидки менеджера.
State: Управляет состоянием автомобиля (доступен, зарезервирован, продан) с помощью CarState.
Установка и запуск
Предварительные требования

Java 8+
Шаги для установки и запуска проекта

Клонируйте репозиторий:
bash
Copy code
git clone <URL вашего репозитория>
cd CarShop_NYC
Скомпилируйте проект:
bash
Copy code
javac cli/Main.java
Запустите проект:
bash
Copy code
java cli.Main
Использование
После запуска программы через командную строку вы можете использовать следующие команды для взаимодействия с магазином:

catalog — Показать каталог доступных автомобилей, сгруппированных по маркам. Отображается ID, марка и цена каждого автомобиля.
add-to-cart — Добавить автомобиль в корзину, указав ID автомобиля.
view-cart — Просмотреть текущую корзину с ID, описанием и ценой автомобилей.
checkout — Оформить заказ. Все автомобили из корзины удаляются из каталога после оформления.
set-discounts — Применить скидки к автомобилям в корзине. Поддерживаются VIP, сезонные и скидки менеджера.
add-options — Добавить опции к автомобилю в корзине, такие как Sunroof или Sport Package.
pay — Выбрать метод оплаты (PayPal или Credit Card) и провести оплату.
exit — Выйти из программы.
Примеры использования
Пример работы с программой:

Просмотр каталога:
Copy code
catalog
Добавление автомобиля в корзину:
csharp
Copy code
add-to-cart
Введите марку автомобиля: BMW
Введите ID автомобиля: 3
Применение скидок:
arduino
Copy code
set-discounts
Введите номера скидок для применения (например, 1 2): 1 3
Оформление заказа:
Copy code
checkout
Оплата:
Copy code
pay
Выберите способ оплаты: 1 (PayPal) или 2 (Credit Card)
Пример работы паттернов
Composite и Facade — Каталог организован по маркам, каждая из которых содержит список моделей. CarShopFacade скрывает сложность взаимодействия между компонентами и обеспечивает доступ к основным функциям.
Decorator — Позволяет динамически добавлять опции к автомобилям, изменяя их описание.
Chain of Responsibility — Применяет скидки на автомобили в корзине, где каждый обработчик проверяет возможность применения своей скидки.
Дополнительная информация
Проект разрабатывался как учебный и демонстрационный пример использования паттернов проектирования. Он позволяет гибко управлять каталогом автомобилей, корзиной и обработкой заказов, обеспечивая простой и удобный интерфейс для пользователей.

Авторы:

Erik Aktaev
Alana Mukanova
Aruzhan Amanbekova
Примечание: Если у вас возникнут вопросы или предложения, пожалуйста, не стесняйтесь связываться с командой разработчиков. Enjoy your journey with CarShop_NYC! 🚗
