<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>VK</title>
    <link rel="stylesheet" href="/css/styles.css">
    <link rel="stylesheet" href="/css/profile.css">
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
            <li><a href="/MyProfile" id="active">Профиль</a></li>
            <li><a href="/Message">Сообщения</a></li>
            <li><a href="/Notification">Уведомления</a></li>
            <li><a href="/settings">Настройки</a></li>

        </ul>
    </nav>
    <div class="profile">
        <a href="/logout">Выйти</a></li>
    </div>
</header>

<main>
    <div class="news-feed">
        <div class="profile-info">
            <div class="profile-image">
                <img src="/img/Screen/${user.fileName}" alt="">
            </div>
            <div class="profile-details">
                <div class="text_item">${user.firstName}  ${user.lastName}</div>
                <div class="text">email: ${user.email}</div>
                <div class="text">Страна: ${user.country}<br></div>
                <div class="text">Пол: ${user.gender}<br></div>
                <div class="text">Дата рождения: ${user.dateOfBirth}<br></div>
                <div class="text">Профиль: ${user.profile}<br></div>
            </div>
        </div>
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