# guess-number-game

Интерактивная игра на Java SE, где вы угадываете число от 1 до 100! Соревнуйтесь с самим собой или друзьями, отслеживайте попытки и попадайте в топ-5 лучших игроков. Программа сохраняет результаты в файл, показывает рейтинг и позволяет очищать статистику — всё через удобное консольное меню.

✨ Требования: Java SE 8+

🚀 Запуск: 
Склонируйте проект: git clone https://github.com/Sok-ai/guess-number-game
Перейдите в директорию: cd guess-number-game 
Скомпилируйте: javac src/*.java 
Играйте: java -cp src GuessNumberGame

💻 Структура: 
src/GuessNumberGame.java — основная логика игры и меню. 
src/Player.java — модель игрока с именем, попытками и датой. 
src/RatingManager.java — управление рейтингом и работой с файлом results.txt.
