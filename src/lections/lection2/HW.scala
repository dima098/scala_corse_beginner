package  lections.lection2


import DataStorage._

object Main extends App{
    /*  Итак, представим что мы перенеслись на 3 года назад, и нам надо создать КП заново
        начнем с запросов к базе данных :) у вас есть три таблицы(коллекции)
        TODO Кастомные релизы(releases), Пользователи(users) и Кинотеатры(cinemas)
    */

    /*
        Для простоты предположим что пользователь может являться владельцем кинотеатров,
        и за каждым пользователем закреплены релизы

        Схему моделей посмотрите внизу файла в классах
        они описываются в формате
        TODO ИмяПоля: ТипПоля

        Доступ к полям обьектов осуществляется через знак точка
        TODO releaseItem.title
    */

    /* TODO Использовать для работы эти коллекции */
    releases
    users
    cinemas

    /* TODO старт киноплана */

    def findReleaseById(id: Int) /** укажите тип */ = ??? /** реализация */
    def findUserById   (id: Int) /** укажите тип */ = ??? /** реализация */
    def findCinemaById (id: Int) /** укажите тип */ = ??? /** реализация */

    def getAdmins /** укажите тип */ = ??? /** реализация */

    def findReleasesByDate(week: Int, year: Int) /** укажите тип */ = ??? /** реализация */
    def findCinemasByUser (userId: Int) /** укажите тип */ = ??? /** реализация */
    def findUserByCinema(cinemaId: Int) /** укажите тип */ = ??? /** реализация */

    def isCinemasOfUserHasCountry(userId: Int, country: String) /** укажите тип */ = ??? /** реализация */
    def ReleaseTitlesByCinema(cinemaIds: List[Int], week: Int, year: Int) /** укажите тип */ = ??? /** реализация */

    /* TODO задание посложнее :) */

    /* Напишите универсальную ф-ию фильтрации релизов, то есть
    чтобы вы в одной ф-ии могли отфильтровать релизы по длительности, году, юзеру
    TODO Подсказка: Воспользуйтесь лямбда ф-ией (???) => Boolean */

    /* Итак, мы написали ф-ии для работы с БД :) КП уже почти готов, чтобы рубить бабло :) */

    // TODO 1
    /* Напишите ф-тю которая по id выведет информацию о пользователе, его релизах с длительностью > 100 и кинотеатрах за границей,
    если пользователя нет, то вывести информацию об ошибке
    TODO Подсказка: Передавайте обьекты целиком в println - println(releaseItem)
    */

    // TODO 2
    /* Напишите ф-ию, которая для всех администраторов
        вычислит среднее арифметическое длительности их релизов и вернет Map вида (userId -> List(релизы с длительностью больше чем avg))
        TODO Может быть в Scala уже есть ф-ия, которая превращает список в карту ? :)
        TODO Карты тоже можно обрабатывать с помощью map, filter и тд    M.map { case (key, value) => {} }
    */

    // TODO 3

    /*
     Напишите ф-ию, которая получает имена релизов за конкретную неделю, только если среди кинотеатров владельца релиза
     есть Российские,
     и после этого приводит их в верхний регистр
    */

    // TODO 4
    /*
      Напишите ф-ию, которая получает userId, cinemaId и releaseId и возвращает release,
      только в случае, если
      1) Есть пользователь с id
      2) Есть релиз с id и userId
      3) Есть кинотеатр с id и userId
    */

    // TODO 5
    /*
        Напишите ф-ию, которая получает релизы проверяет чтобы их длительность была в границах 90 - 130,
        владелец был администратором
        и сделать из них Map("Первая буква названия" -> List(Release))
        TODO Подсказка Строки это тоже коллекции, можно воспользоваться head или headOption, если вы не уверены в том что строка непустая
    */
}

/** Данные в нашей БД  */

object DataStorage {
    val users       = List(
        User(0, "Андрей" , "Джобс"  , isAdmin = true  ),
        User(1, "Иван"   , "Чулаков", isAdmin = false ),
        User(2, "Игорь"  , "Гейтс"  , isAdmin = true  ),
        User(3, "Сергей" , "Маск"   , isAdmin = false ),
        User(4, "Дмитрий", "Джонс"  , isAdmin = false ),
        User(5, "Петр"   , "Тайсон" , isAdmin = false ),
        User(6, "Антон"  , "Фелпс"  , isAdmin = true  ),
        User(7, "Арсений", "Рено"   , isAdmin = true  )
    )

    val cinemas     = List(
        Cinema(0,  0, "KinoMax"         , "Россия"  ),
        Cinema(1,  0, "Ростов"          , "Россия"  ),
        Cinema(2,  2, "Синема5"         , "Россия"  ),
        Cinema(3,  7, "AjaxCinema"      , "США"     ),
        Cinema(4,  6, "Большой"         , "Россия"  ),
        Cinema(5,  0, "PARIS-CINEMA"    , "Франция" ),
        Cinema(6,  2, "Мираж"           , "Россия"  ),
        Cinema(7,  6, "Аврора"          , "Россия"  ),
        Cinema(8,  7, "Urania"          , "Венгрия" ),
        Cinema(9,  2, "Electric Cinema" , "Англия"  ),
        Cinema(10, 6, "Orinda"          , "США"     )
    )

    val releases    = List(
        CustomRelease(0, "Черепашки Ниндзя"                 , 132, 0, 12, 2013),
        CustomRelease(1, "Как стать Scala Senior"           , 154, 6, 20, 2016),
        CustomRelease(2, "Люди Икс: Дни минувшего будущего" , 133, 7, 19, 2014),
        CustomRelease(3, "Гонка"                            , 148, 3, 34, 2015),
        CustomRelease(4, "СпанчБоб"                         , 98,  5, 15, 2013),
        CustomRelease(5, "Кто я?"                           , 103, 7, 31, 2016),
        CustomRelease(6, "Кухня в Париже"                   , 111, 3, 21, 2015),
        CustomRelease(7, "Побег из Шоушенка"                , 89,  2, 18, 2014),
        CustomRelease(8, "На крючке"                        , 106, 0, 5,  2013),
        CustomRelease(9, "Снайпер"                          , 115, 1, 16, 2015),
        CustomRelease(10, "Симпсоны в кино"                 , 129, 1, 14, 2016),
        CustomRelease(11, "Форест Гамп"                     , 145, 0, 32, 2011),
        CustomRelease(12, "Железный человек 3"              , 162, 6, 33, 2013),
        CustomRelease(13, "Мстители 2"                      , 128, 4, 12, 2015),
        CustomRelease(14, "Без лица"                        , 137, 3, 34, 2016),
        CustomRelease(15, "Час пик 2"                       , 141, 4, 16, 2009),
        CustomRelease(16, "Игра Эндера"                     , 121, 2, 38, 2012),
        CustomRelease(17, "Обмани меня"                     , 147, 1, 19, 2011),
        CustomRelease(18, "Быстрее пули"                    , 122, 0, 20, 2015)
    )
}

/** Модели нашей БД */

case class User(
   id: Int,
   firstName: String,
   lastName: String,
   isAdmin: Boolean
) {
    override def toString = s"$firstName $lastName c id = $id" + (if (isAdmin) " является администратором" else "")
}

case class Cinema(
    id: Int,
    userOwnerId: Int,
    title: String,
    country: String
) {
    override def toString = s"Кинотеатр с id = $id, название '$title',\nстрана $country, id владельца = $userOwnerId"
}

case class CustomRelease(
    id: Int,
    title: String,
    duration: Int,
    userId: Int,
    weekStart: Int,
    yearStart: Int
) {
    override def toString = {
        s"Релиз $title с id = $id, длительность = $duration\n" +
            s"Владелец id = $userId, неделя старта $weekStart, год старта $yearStart\n"
    }
}