package homework

object Selyutin extends App{
        /* Вспомогательные функции */

    def value(condition: (Double, Double) => Boolean, params: Double*): Double = {
        if (params.nonEmpty) {
            var result = params.head
            for (value <- params if condition(value, result)) {
                result = value
            }
            result
        } else {
            0.0
        }
    }

    def isSimple(value: Int): Boolean = {
        var flag = true
        for (i <- 1 to value / 2 if value % i == 0 && i != 1) {
            flag = false
        }
        flag
    }

    def sum(params: Double*): Double = {
        var result = 0.0
        for (value <- params) {
            result += value
        }
        result
    }

    /*                          TODO Паттерн стратегия                                      */
    def strategy(
        printer: Double => Unit,
        transformer: Seq[Double] => Double,
        params: Double*
    ): Unit = printer(transformer(params))


    /*                                      TODO Min                                                          */

    def min(params: Double*): Double = value(_ < _, params: _*)

    /*                                      TODO Max                                                          */

    def max(params: Double*): Double = value(_ > _, params: _*)

    /*                                      TODO Среднее арифметическое                                      */

    def avg(params: Double*): Double = sum(params: _*) / params.length

    /*                                      TODO Принтер                                     */

    def printerLowerCase(value: Double): Unit = println(s"value - $value")

    /*                                      TODO Принтер                                     */

    def printerUpperCase(value: Double): Unit = println(s"VALUE - $value")

    /*                                      TODO Вывод простых чисел от 1 до N                                     */
    def printSimple(n: Int): Unit = {
        var count = 0
        for (i <- 1 to Int.MaxValue if isSimple(i) && count < n) {
            count += 1
            println(i)
        }
    }

    strategy(printerLowerCase, avg, 1.0 ,2.0 ,3.0)
    strategy(printerUpperCase, max, 1.0 ,2.0 ,3.0)
    strategy(printerUpperCase, min, 1.0 ,2.0 ,3.0)

    printSimple(20)
}
