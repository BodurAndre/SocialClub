<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>VK</title>
    <link rel="stylesheet" href="/css/styles.css">
    <link rel="stylesheet" href="/css/test.css">
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
        <a href="/logout">Выйти</a>
    </div>
</header>

<main>
    <div class="news-feed">
        <div class="image-text-block">
            <form id="post-form">
                <div class="text">
                    <textarea id="post-text" placeholder="Введите текст поста..."></textarea>
                    <button type="submit">Опубликовать</button>
                    <input type="file" id="image-upload" accept="image/*">
                    <label for="image-upload">Выберите изображение</label>
                </div>
            </form>
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