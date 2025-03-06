# ReligionPlugin

Плагин добавляет религии которые добавляют вечные еффекты, игроки должны купить религии за ресурсы, также религию игроку можно выдать командой, Администраторы могут удалять религии у игрока, добавлять, смотреть.
Также есть 4 настраимаевых файла:
config.yml - все значения
message.yml - все сообщения
playerData.yml - значения религий игроков
religions.yml - все Религии

# Команды

/religion - Посмотреть свою религию
/religion clear [player] - Очистить религию игрока (Доступно с прав ReligionPlugin.clear или ReligionPlugin.*)
/religion set [player] [religion] - установить религию игроку (Доступно с прав ReligionPlugin.setReligion или ReligionPlugin.*)
/religion buy [religion] - купить религию за ресурсы

# Права

ReligionPlugin.* - даёт доступ ко всем командам
ReligionPlugin.clear - Даёт доступ к команде /religion clear
ReligionPlugin.setReligion - Даёт доступ к команде /religion set

# Плейсхолдеры

%religions_religion% - религия Игрока

# Плагин делал - CaptainFanky 7 часов подряд
