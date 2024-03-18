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
            <li><a href="/MyProfile" >Профиль</a></li>
            <li><a href="/Message">Сообщения</a></li>
            <li><a href="/Notification">Уведомления</a></li>
            <li><a href="/settings" id="active">Настройки</a></li>
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
                <#if mode="true">
                    <img src="/img/Screen/${user.fileName}" alt="">
                <#else>
                    <img src="/img/standart_profile.png" alt="">
                </#if>
                <form action="/upload" method="post" enctype="multipart/form-data">
                    <input type="file" name="file"><br>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <input type="submit" value="Добавить аватарку"><br>
                </form>
            </div>
            <div class="profile-details">
                <form action="/editAccount" method="post">
                    <div class="text">Имя: <input type="text" name="firstName" value="${user.firstName}"/></div>
                    <div class="text">Фамилия: <input type="text" name="lastName" value="${user.lastName}"/></div>
                    <div class="text">email: <input type="text" name="email" value="${user.email}"/></div>
                    <div class="text">Пол:
                        <select id="gender" name="gender" th:value="${user.gender}" class="dropdown">
                            <option value="Male">Мужской</option>
                            <option value="Female">Женский</option>
                        </select></div>
                    <div class="text">Профиль:
                        <select id="gender" name="profile" th:value="${user.profile}" class="dropdown">
                            <option value="Public">Public</option>
                            <option value="Private">Private</option>
                        </select>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <br><br><input type="submit" value="Сохранить изменени"><br><br>
                </form>
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