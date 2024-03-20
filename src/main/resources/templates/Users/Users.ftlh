<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>VK</title>
    <link rel="stylesheet" href="/css/styles.css">
    <link rel="stylesheet" href="/css/user.css">
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
            <li><a href="/" id="active">Главная</a></li>
            <li><a href="/MyProfile">Профиль</a></li>
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
        <#if users??>
            <#list users as user>
                <a class="profile-info" href="/account/${user.id}">
                    <div class="profile-image">
                        <#if user.profilePhotoUrl == "true">
                            <img src="/img/${user.email}/${user.fileName}" alt="">
                        <#else>
                            <img src="/img/standart_profile.png" alt="">
                        </#if>
                    </div>
                    <div class="profile-details">
                        <div class="text_item">${user.firstName}  ${user.lastName}</div>
                        <#if user.profile != "Private">
                            <div class="text">email: ${user.email}</div>
                            <div class="text">Страна: ${user.country}<br></div>
                            <div class="text">Пол: ${user.gender}<br></div>
                            <div class="text">Дата рождения: ${user.dateOfBirth}<br></div>
                        <#else>
                            <div class="text">Профиль закрытый</div>
                        </#if>
                    </div>
                </a>
            </#list>
        <#else>
            <p>${message}</p>
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




