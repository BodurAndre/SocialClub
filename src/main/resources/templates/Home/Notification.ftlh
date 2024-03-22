<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>VK</title>
    <link rel="stylesheet" href="/css/styles.css">
    <link rel="stylesheet" href="/css/notification.css">
</head>
<body>
<header>
    <div class="logo">
        <img src="/img/VK-logo.png" alt="VK Logo">
    </div>
    <form id="logout-form" class="search-bar" action="/search" method="get">
        <input type="text" name="title" placeholder="Search...">
        <input class="btn-logout" type="submit" value="Поиск">
    </form>
    <nav>
        <ul>
            <li><a href="/">Главная</a></li>
            <li><a href="/MyProfile">Профиль</a></li>
            <li><a href="/Message">Сообщения</a></li>
            <li><a href="/Notification" id="active">Уведомления</a></li>
            <li><a href="/settings">Настройки</a></li>
        </ul>
    </nav>
    <div class="profile">
        <a href="/logout">Выйти</a></li>
    </div>
</header>

<main>
    <div class="notification-feed"> <!-- Изменил класс на notification-feed -->
        <#if users??>
            <#list users as user>
                <div class="notification-item"> <!-- Изменил класс на notification-item -->
                    <div class="notification-header"> <!-- Изменил класс на notification-header -->
                        Заявка в друзья
                    </div>
                    <div class="profile-image">
                        <#if user.profilePhotoUrl == "true">
                            <img src="/img/${user.email}/${user.fileName}" alt="">
                        <#else>
                            <img src="/img/standart_profile.png" alt="">
                        </#if>
                    </div>
                    <div class="profile-details">
                        <div class="text-item">${user.firstName}  ${user.lastName}</div> <!-- Изменил класс на text-item -->
                        <div class="action-link">
                            <a href="/friendAdd/${user.getId()}">Принять</a> <!-- Добавил класс action-link -->
                        </div>
                        <div class="action-link">
                            <a href="">Отклонить</a> <!-- Добавил класс action-link -->
                        </div>
                    </div>
                </div>
            </#list>
        </#if>
    </div>
</main>


<footer>
    <div class="footer-nav">
        <a href="#">О проекте</a>
        <a href="#">Контакты</a>
    </div>
    <div class="footer-text">© 2024 VK. All rights reserved.</div>
</footer>
</body>
</html>