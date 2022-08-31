# domino-words-sample
Пример к статье https://javarush.ru/groups/posts/3923-nemnogo-o-grafakh-i-slovakh---domino-chastjh-1
<br>
Можно запустить из любимой IDE (например, IntelliJ IDEA).
Компиляция из командной строки:
```
javac -encoding utf8 EulerPath.java
```
Запуск:
```
java -Dfile.encoding=UTF-8 EulerPath
```
Примеры входных данных для запуска из IDE:
```
String[] inputSequence = {"Адлер", "Рыбинск", "Курган", "Нарьян-Мар", "Рим", "Мурманск", "Константинополь"};

String[] inputSequence = {"alpha", "elephant", "kick", "linea", "android", "eels", "kill", "sum", "eye", "spud", "drink", "even", "bee", "dad", "num", "tea", "test", "sims", "apple", "mob", "mate"}; //Содержит эйлеров путь

String[] inputSequence = {"test", "sum", "num", "kill", "sims", "mate", "even", "dad", "android", "eye", "kick", "elephant", "drink", "alpha", "eels", "bee", "mob", "linea", "tea", "apple"}; //Содержит эйлеров цикл
```
