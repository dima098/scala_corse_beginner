package raw_lections.lection2.lection3

import java.security.MessageDigest

object Main extends App {
   /*                       TODO (Кратко про ООП) Классы, обьекты, case классы, implicit значения, тип Either[A, B]
       Scala так же поддерживает Обьектно Ориентированную парадигму программирования,
       то есть три слова которые вы должны даже в три часа ночи четко вспомнить Полиморфизм, Инкапсуляция, Наследование
       Мы не будем подробно останавливать на том что такое ООП и зачем оно такое придумано,
       просто пройдемся по тому как это реализовано в Scala, и как это нам помогает в работе
       Детский сад кончился. это Scala :-)
                                          */

    /* Классы определяются с помощью слова class ( неожиданно, правда ? :) )
    затем в круглых скобках описываем поля класса
    в фигурных скобках методы класса и поля
    Экземпляры этого класса создаются с помощью оператора new
    */

    class ScalaProgrammer( // определяем класс нашего программиста
         val isKnowOOP: Boolean = true,
         var name: String,
         workInTheKinoplan: Boolean // это поле будет недоступно когда мы будем к нему обращаться у экземпляра, тк xz что это :)
    ) {
        val canPushForceMaster = true

        def createPullRequestWithShitCode(data: String, rewievers: Set[String]) = {
            if (canPushForceMaster) {
                println("Какой такой pull request ???")
            } else {
                println(s"Создан pull request для $data")
            }
        }

        def writeInPhp(data: String) = println("oO, Какой такой PHP?")

        /* TODO Некоторые полезные ф-ии, которые есть уже из корбки в case class (смотреть ниже что такое case class)
        override def toString = s"ScalaProgrammer($isKnowOOP,$name,$workInTheKinoplan)"

        override def equals(that: Any): Boolean = {
            that match {
                case scalaProgrammer: ScalaProgrammer => {
                    name.toLowerCase == scalaProgrammer.name.toLowerCase
                }
                case _ => false
            }
        }

        def copy(isKnowOOP: Boolean = isKnowOOP, name: String = name, workInTheKinoplan: Boolean = workInTheKinoplan ) = {
            new ScalaProgrammer(isKnowOOP, name, workInTheKinoplan)
        }

        */
    }

    /* Экземпляры этого класса создаются с помощью оператора new
       Также легко можно использовать значения по умолчанию и именованные параметры
       Все создается просто, как 2 пальца об асфальт
       Обращение к полям и вызов функций через символ .
    */
    val scalaProgrammer = new ScalaProgrammer(true, "Coffee addicted person", workInTheKinoplan = true)
    scalaProgrammer.writeInPhp("Noooooo")

    /* TODO                                 Object                                                                  */

    /* В Scala есть понятие обьекта, другими словами это статический класс, или синглтон
    там мы обьявляем поля и методы самого класса а не его конкретного экземпляра
    которые будут доступны без создания экземпляра этого типа
*/

    object GalaxyNote7 {
        val osVersion = "5.0"
        def tranform2Dynamite = println("Boom")
    }

    GalaxyNote7.tranform2Dynamite

    /* Если мы обьявляем обьект компаньон с тем же именем что и созданный класс, то мы получаем обьект-компаньон,
      который может нам дать неплохие ништяки при написании кода. 2 наиболее юзабельных это методы apply и unapply
     */

    object ScalaProgrammer {
        //Я просто оставлю это здесь :)
        val version: String = "Came after assembler, still believe in MS-DOS"

        /* Позволяет не писать слово new при создании класса
         val lightProger = ScalaProgrammer(...)
         */
        def apply(isKnowOOP: Boolean = true, name: String, workInTheKinoplan: Boolean): ScalaProgrammer = {
            new ScalaProgrammer(isKnowOOP, name, workInTheKinoplan)
        }

        /* Метод, который разбирает экземпляр класса на кортеж параметров,
        нужно для удобства работы с case match
        Теперь мы можем в очень удобной форме матчить наши экземпляры класса, пример чуть ниже
        */
        def unapply(scalaProgrammer: ScalaProgrammer): Option[(Boolean, String, Boolean)] = {
            Some( (scalaProgrammer.isKnowOOP, scalaProgrammer.name, true) )
        }
    }

    /* Работает благодаря apply методу в обьекте компаньоне */
    val improvedScalaProgrammer = ScalaProgrammer(
        isKnowOOP = true,
        s"Scala addicted person ${ScalaProgrammer.version}",
        workInTheKinoplan = true
    )
    improvedScalaProgrammer.createPullRequestWithShitCode("Some( 0 ).map(_ + 2)", Set())

    improvedScalaProgrammer match {
        /* Этот case работает благодаря методу unapply в обьекте компаньоне */
        case ScalaProgrammer(true, _, _)                    => {
            println(" We have found him !!! :) ")
        }
        /* Этот case работает благодаря методу unapply в обьекте компаньоне */
        case ScalaProgrammer(_, name, true) => {
            println(s" Easy match :) for $name ")
        }
        case programmer if programmer.canPushForceMaster    => {
            println(" Stop him!! He is going to make push --force to master!!! ")
        }
        case _                                              => {
            println( "No way :( ")
        }
    }

    /* TODO                                             CASE CLASS                                                          */

    /* case class это синтаксический сахар в Scala, чтобы сделать жизнь программиста еще проще :)
     при добавлении слова case мы из коробки сразу получаем
     1) Все параметры класса в скобках получают по умолчанию val, но можно явно их переопередлить
     2) Создается обьект компаньон с apply и unapply методами
     3) Создаются ф-ии toString, copy, equals, и еще несколько полезных, но мало нами использумых
     */

    /* Как вы могли заметить мы получили все тоже самое, но мы вычистили все "ненужное", Mr.Proper бы гордился вами */
    case class FullStackProgrammer(
      isKnowScala:  Boolean,
      isKnowJs:     Boolean,
      isKnowNoSQL:  Boolean,
      isKnowSQL:    Boolean,
      hasTimeToPlayKicker: Boolean = true
    ) {
        def playKicker() = {
            if (hasTimeToPlayKicker) {
                println("Пока билдится можно поиграть")
            } else {
                println("Поставлю-ка я билдится")
            }
        }
    }

    /* Можно также описать дополнительный функционал обьекта компаньона */
    object FullStackProgrammer {
        val isHeSleep = false
    }

    /* TODO                              Implicit values                                                            */

    /* В Scala существуют неявные(implicit) значения, значения которые можно не указывать явно,
     и они будет взяты из мест куда сможет достучаться ваш код где обьявлено это неянвое значение */

    //функция с implicit параметром (нужно указывать в отдельных скобках)
    def funcWithImplicit(data: String)(implicit code: Int) = {
        println(s"$data $code")
    }

    //Можем явно передать два значения, тогда все выглядит почти также как мы привыкли
    funcWithImplicit(" DATA ")(200)

    //А теперь зададим неявное значение отдельным выражением
    implicit val defaultCode = 400
    /* И о чудо !! :) Мы можем вызвать нашу ф-ия с одним параметром, а второй параметр она найдет сама,
    ну если конечно он где то есть и можно туда достучаться, иначе ошибка компиляции и Давай до свидания со своими Implicit
    */
    funcWithImplicit(" Implicit call ")

    /* Но честно признаться, такое специально не напишешь :)
    Это часто используется как уже готовая реализация в библиотеках, но это также нужно использовать осторожно,
    и не злоупотреблять этим, а то потом будете бегать искать какой implicit где появляется */


    /* А вот теперь то что мы активно применяем в наших проектах
        Мы можем расширить любой класс дополнительными полями и методами
        Если с классами которые реализованы нами, это прекрасно решается наследованием,
        то с классами, которые для нас черный ящик это иногда может спасти вам жизнь :)
    */
    object Helper {

        /* TODO История о том Как заставить программиста писать на PHP
            1) Определение таких классов начинается со слова implicit
            2) Такой класс должен определяться внутри обьекта
            3) У них всего один аргумент - переменная того типа, который мы хотим расширять
            4) Вы идеальны :) Теперь вы можете писать на PHP, D'oh!
        */
        implicit class HackedScalaProgrammer(hackedScalaProgrammer: ScalaProgrammer) {
            def sudoWriteInPHP(data: String) = println("Ok, boss :(")
        }

        implicit class StringExtended(value: String) {
            def sha256 = MessageDigest.getInstance("SHA-256").digest(value.getBytes).map("%02x" format _ ).mkString
        }

        /* Иногда у нас ф-ия или конструктор класса принимает один тип параметров,
         а нам в какой-то момент захотелось передать другой тип,
          так просыпаешься с утра и первая мысль - Я хочу еще и String кроме Set[String] передавать
          Тут у вас есть множество вариантов, определять еще один конструктор класса, писать еще одну функцию,
           использовать тип Any и потом match case, ну короче г*внокодить :) */

        // тут нам на помощь снова приходят implicit
        // достаточно определить ф-ию конвертер которая принимает один тип и возвращает другой
        // ее уже необязательно указывать внутри обьекта, это сделано просто для удобства
        implicit def string2Set(string: String): Set[String] = string.split(",").toSet

    }

    import Helper._

    improvedScalaProgrammer.sudoWriteInPHP(" Why world is so cruel ??? ")
    improvedScalaProgrammer.createPullRequestWithShitCode(" shit code ", "А,Б,С")

    "Все секреты КИНОплана".sha256

    /* TODO                              Тип Either[A,B]                                                                        */
    /* Помимо Option типа для обработки ошибочных ситуаций существует тип Either[A,B], который так же
    как и Option работает по принципу Кота Шредингера, то есть находится в одном из двух состояний, в нашем
     случае там хранится либо значения типа A (называется Left) либо значение типа B (называется Right)
     Применяется в случае когда нам надо помимо факта ошибки получить информацию о самой ошибке
     В Left обычно пишут ошибку, а в Right успешный результат
     сравните 2 случая
    */

    val writeCodeOption: Option[Int] = if (true) Some(200) else None // мы не знаем что это за ошибка, просто знаем что она случилась

    // Лень - 80 level
    // Будь бунтарём, сломай систему
    def OK_GOOGLE_ASC_SIRI_ABOUT_WEATHER(lazyLevel: Int = 80): Either[String, Int] = if (lazyLevel == 80) {
        Right(200)
    } else if (lazyLevel == 50) {
        Left(" What are you talking about ??? ") // Есть описание ошибки
    } else {
        Left(" ??? ") // Есть описание ошибки, правда нужно конечно что-нибудь информативнее
    }

    // Очень часто мы используем наши PartialFunctions, чтобы проверить содержимое Either
    // В данном случае с помощью match

    OK_GOOGLE_ASC_SIRI_ABOUT_WEATHER() match {
        case Right(code) => println(code)
        case Left(error) => println(error)
    }

    /* В случае, если вам не важно что там может быть в Left, можно попробовать привести это к Right */
    OK_GOOGLE_ASC_SIRI_ABOUT_WEATHER().right.toOption.map(_ + 1).getOrElse(0)

    /* Ну и если у вас очень маленький код на обработку обоих ситуаций, то fold по мне ваш лучший друг */
    OK_GOOGLE_ASC_SIRI_ABOUT_WEATHER().fold(_ => "error", _ => "success")

    /*                                      TODO ДОПОЛНИТЕЛЬНАЯ ИНФОРМАЦИЯ                                                  */
    /* TODO                                Trait и немного про наследование                                                 */

    trait Productive {
        val description = "Upgrade for lazy people"
        val hours: Int
        def drinkCoffee(): Unit
        def makeInsomnia(): Unit = (1 to hours).foreach(_ => drinkCoffee())
    }

    trait SuperAbility {
        def xRay:             Unit
        def fireBySight:      Unit
        def becomeProgrammer: Unit
    }

    class Person {
        def say(msg: String) = println(msg)
    }

    class LazyPerson(name: String) extends Person with Productive with SuperAbility {
        override val hours: Int             = 25
        override def drinkCoffee(): Unit    = say(" Drink coffee and write code ")
        override def xRay: Unit             = say(" I can compile code just with sight ")
        override def fireBySight: Unit      = say(" Useless ability :( ")
        override def becomeProgrammer: Unit = say(" Just DO it! Yes you can! (or not) ")
    }

    new LazyPerson(" I'm too lazy to say my name ").makeInsomnia()
}

