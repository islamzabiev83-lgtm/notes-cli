# Сборка образа
docker build -t notes-cli:dev .

# Создание папки для данных на хосте
mkdir -p data

# Запуск контейнера с volume
docker run --rm -v "$PWD/data:/app/data" notes-cli:dev --cmd=add --text="Первая заметка"
docker run --rm -v "$PWD/data:/app/data" notes-cli:dev --cmd=list