package raw_lections

/**
  * Created by GInger on 03.10.16.
  */
object Main extends App{
    //                              TODO   Лекция 2 Коллекции, операции над ними, match case, Option[A]
    /*
        TODO В Scala можно выделить 2 типа коллекций: mutable и immutable
        опять же по аналогии с переменными мы стараемся не использовать mutable коллекции
        Ты можешь использовать mutable коллекции, только в случае, если это решает какую - то мега проблему,
        типа глобального потепления, если нет, то забудь слово MUTABLE
        TODO Детский сад кончился это Scala
    */

    /*

    /* TODO                                         Типы коллекций                                              */
    Наиболее часто употребляемые нами это
     */
    val list = List[String]("Never", "will", "be", "mutable") // Связанный список

    val set  = Set[String]( // Множество (каждый элемент уникален)
        "You can copy as mush as you want, only one will be here",
        "You can copy as mush as you want, only one will be here",
        "You can copy as mush as you want, only one will be here",
        "You can copy as mush as you want, only one will be here"
    )

    val map = Map[Int, String]( // Словарь/Хеш кому как нравится
        0 -> "mutable",
        1 -> "immutable",
        2 -> "mutable",
        3 -> "immutable",
        4 -> "mutable, D'oh"
    )

    // И просто знайте что еще есть такие коллекции

    val seq  = Seq[Int](1, 2, 3, 4) // Последовательность

    val vector = Vector[Double](1.0, 3.0) //Вектор, безумно быстрая штука

    /* На самом деле это все базовые типы, и у каждого из них есть подтипы, заточенные под свой определенный случай
       Более того, Seq это родитель для Vector и List

       Кому кайф вынести себе мозг, то можно более подробно узнать об этом на картинке,
       но на первых парах это не самые важные знания, скорее всего вы многого сейчас не поймете,
       и убежите обратно писать на Perl/Js :)

       http://docs.scala-lang.org/resources/images/collections.immutable.png

       */

    /* TODO Ты можешь быть бесконечно прав, но какой в этом смысл, если ты используешь mutable коллекции ? */

    // Применяем лямбду к каждому значению коллекции и возвращаем новую коллекцию
    list.map(_.toUpperCase)
    // Применяем лямбду к каждому значению коллекции но ничего не возвращаем, еврейский вариант map :)
    seq.foreach(number => println(number + 100))
    // Возвращает коллекцию где будут элементы, которые подходят под лямбду-условие
    set.filter(_.startsWith("You"))

    //В случае, если у вас есть вложенность контейнеров, и вам надо применить лямбду к каждому элемента внутреннего контейнера
    //а потом сложить внутренние контейнеры и привести тип общей коллекции к изначальному типу
    val flattenList: List[String] = List(list, list, list).flatMap(item => item.map(_.toLowerCase))

    // Если нужно просто проверить наличие элемента, то пользуемся contains
    val contains:     Boolean = set.contains("Just do(find) it!")
    // Если заказчик говорит TODO "А я хочу киноплан большой и маленький"
    val existsResult: Boolean = list.exists(_.toLowerCase == "kinoplan")
    // А это уже наши планы, чтобы везде был Киноплан :)
    val forAllResult: Boolean = list.forall(_.toLowerCase == "kinoplan")


    /*                                          TODO MATCH CASE                                                         */

    /* Заместо switch в Scala сделали match case конструкцию,
    на самом деле можно использовать только case но об этом чуть позже
    */

    val matchesResult = "Switches are universe evil" match {
        case s: String if s.startsWith("Switches")  => {
            println("String has matched with condition")
            "really evil"
        }
        case s: String if s.split(" ").length == 4 => {
            println("String has matched fully")
            "really evil"
        }
        case _ => {
            "None of them"
        }
    }

    /* С кортежами */

    (10, 4, "oops") match {
        case (_, _, s: String) => {
            println(s)
        }
        case (10, center, _) if center == 3 => {
            println(center)
        }
    }

    /* Так же можно например делать с коллекциями */

    val newList = List("Impossible", "But", "Fact") match {
        case head :: tail => {
            head.toUpperCase :: tail
        }
        case Nil => {
            List()
        }
    }

    /* TODO ************************************СЛОЖНАЯ ЧАСТЬ*********************************************** */

    /* Пссс, программист! Не хочешь немного математики ?
        Если вы заметили, мы тут обьявляем те же самые функции внутри match, но это уже больше математические функции,
         так как мы задаем их область определения (на каких значениях функция имеет значение)
         Каждый case это ЧАСТИЧНО ОПРЕДЕЛЕННАЯ ФУНКЦИЯ(PartialFunction), где мы указываем на каких значениях она существует
         Мы будем очень часто встречать с этим понятием в Scala
         Если мы описываем все возможные значения, мы получаем объединение ЧОФ-ий которое покрывает все значения и все хорошо, и можно не переживать,
         Но если мы указываем только часть значений, то мы получаем объединение ЧОФ-ий которое не покрывает всех значений и тут могут возникнуть проблемы
         ,
         Наши привычные лямбды и ф-ии имеют тип Function, а PartialFunction это его наследник , а значит можно делать вот так
        */

    List("Can", "Break", "Your", "Mind") map {
        case value => {
            value.toUpperCase
        }
    } filter {
        case value => value.startsWith("C")
    }

    /* Можно тоже самое сделать если добавить условие прям в первую запись , и тут мы как раз получаем
        PartialFunction так как мы не определили ее значения для всего что не выполняет условие if value.startsWith("C")
    */

    List("Can", "Break", "Your", "Mind") map { // TODO Will throw an exception if we find some value out of condition :(
        case value if value.startsWith("C") => {
            value.toUpperCase
        }
    }

    /* Для PartialFunction существует специальный вариант map */

    List("Can", "Break", "Your", "Mind") collect { // TODO Выберет только те значения, которые попадают под условие и для них вернет новый список
        case value if value.startsWith("C") => {
            value.toUpperCase
        }
    }

    /* TODO ************************************************************************************************ */


    /*                                          TODO Option[A]                                                          */

                                                                                            /*
                                                                                                Do you believe in life
                                                                                                After Null?
                                                                                            */

    /* По опыту Java очень легко выстрелить себе в ногу, получив неожиданно null в каком-то месте
    Scala частично решиила эту проблема, выдав вам этакий бронижилет для ноги,
    но только для одной, так что все еще надо быть бдительными */

    /* Если вы не уверены есть ли какое значение у переменной, допустим вы получаете обьект из базы по по его id,
     и разумеется никто не может вам гарантировать, что он на самом деле есть в базе */

    /*
        у вас 2 варианта -
        1) Пойти к гадалке, чтобы она вам точно сказала есть там что то или нет
        2) Использовать Option значения, когда вам терзают смутные сомнения,
        что вы уже навели пистолет себе в ногу, но еще не выстрелили

        Смысл Option такой же как Кот Штредингера, пока мы не откроем коробку, внутри у нас либо
        TODO None
        либо
        TODO Some[Тип значения]
    */

    /* Рассмотрим этот пример на примере функции find */

    val mayBeList = List("In", "Option", "We", "Trust")

    val result: Option[String] = mayBeList.find(_.toLowerCase == "option")
    //На данный момент только вселенной известно есть ли там такое значение или нет
    // TODO Там сейчас находится либо None либо Some[String]

    println(result) //Some(Option)

    // И конечно же наш друг Мартин дал нам кучу способов, чтобы получить
    // то что скрыто в недрах функциональщины или узнать что там ничего нет

    /* Опять же покажу наиболее распространенные в КП способы
        TODO 1) Для тех кто знает толк в извращениях можно использовать всем нам известный if
        Специально для таких случаев в офисе лежит бита
    */

    if (result.nonEmpty) { // так же есть ф-ия isDefined(аналог) и isEmpty(противоположность)
        result.get
    } else {
        "А мы говорили, что надо идти к гадалке..."
    }

    /* TODO 1.2 С помощью case match, лучше, но тоже не кайф, если честно :( */

    val caseOption = result match {
        case Some(value) => value
        case None => "None"
    }

    /*
    TODO 2) В Функциональном стиле
    Я не говорил до этого, но Option это контейнер из одного элемента
    а значит мы можем применить к нему все те же ф-ии что и к коллекциям
    */

    /* Мы пишем лябду, которая сработает только когда внутри что-то есть */
    result.map(_.toLowerCase).getOrElse("functional approach is nice")
    /* на случай если вам не надо никак трансформировать значение, если оно есть, то можно просто
     вызвать getOrElse
    */
    result.getOrElse("yeah...options")

    /* Можно также filter, find, flatMap и много других функций
        Работая с Option мы теперь можем спокойно представлять коллекцию, и абстрагироваться от
        всей внутренней реализации
    */
    result.filter(_ == "Option is").find(_ == "like a collection")

    /* TODO 3) Можно chain-ить option-ы
        Когда вы уже стали Option мастером, и вам начинает резать глаз запись     */
        val first = Some("Easy")
        val second = Some("Options")

        first.flatMap(f => {
            second.map(
                s => f + s
            )
        }).getOrElse("Shit :( Not easy...")

    /* То здесь неожиданно нам на помощь приходит наш позабытый FOR */

    (for {
        f <- first
        s <- second
    } yield {
        f + s
        /* Мы попадаем сюда только если каждое значение указанное в for является Some а не None */
    }).getOrElse("Really easy :)")

    /* Ну и как бонус мне нравится еще такой вариант проверки */

    result.fold("Best but NONE :(")(some => some + "BEST OF THE BEST")

    /* в первых скобках указываете значение для случая None, а в соседних лямбду для случая Some */

    /* При работе с Map мы как раз получаем Option при запросе значения по ключу */


}
