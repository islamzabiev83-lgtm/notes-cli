# Notes CLI - Консольная утилита для заметок

**Автор:** [islamzabiev83-lgtm](https://github.com/islamzabiev83-lgtm)

Консольное Java-приложение для управления текстовыми заметками.

## 📦 Версии
- **v1.0.0** - MVP: добавление заметок (`add`) и вывод списка (`list`)
- **v1.1.0** - Добавлена новая команда (удаление или подсчёт)

## 🚀 Запуск

### Локально (без Docker)
```bash
# Компиляция
javac src/com/example/*.java

# Добавление заметки
java -cp src com.example.App --cmd=add --text="Купить хлеб"

# Вывод всех заметок
java -cp src com.example.App --cmd=list