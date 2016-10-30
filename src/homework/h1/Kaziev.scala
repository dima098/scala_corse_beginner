object Kaziev extends App {
  val num = Seq[Double](1, 2, 3, 4, 5)

  //1) Написать функцию поиска минимума из списка параметров функции ( params: Seq[Double] )
  def findMin(params: Seq[Double] = Seq(0)) = {
    params.min
  }
  println("Task I")
  println("Min: ", findMin(num))

  //2) Написать функцию поиска максимума из списка параметров функции ( params: Seq[Double] )
  def findMax(params: Seq[Double] = Seq(0)) = {
    params.max
  }
  println("Task II")
  println("Max: ", findMax(num))

  //3) Написать функцию поиска среднего арифметического из списка параметров функции ( params: Seq[Double] )
  def findAvg(params: Seq[Double] = Seq(0)) = {
    params.sum / params.size
  }
  println("Task III")
  println("Avg: ", findAvg(num))

  //TODO Функция вывода
  //4) Написать несколько функций (Double) => Unit которые выводят на экран значение в какой то особой форме (см. пример в лекции )
  def printMin(value: Double = 0): Unit = {
    println(s"Min = $value")
  }

  def printMax(value: Double = 0): Unit = {
    println(s"Max = $value")
  }

  def printAvg(value: Double = 0): Unit = {
    println(s"Avg = $value")
  }
  println("Task IV")
  printMin(findMin(num))
  printMax(findMax(num))
  printAvg(findAvg(num))

  //5) И теперь реализуем паттерн Стратегия
  //Создайте функцию которая принимает функцию обработки, функцию вывода и список параметров и
  //  выводит на экран с помощью функции вывода результат применения функции обработки к списку параметров
  //Предусмотрите значения по умолчанию
  def strategy(processing: Double => Unit,
               output: Seq[Double] => Double,
               params: Seq[Double] = Seq(0)
              ): Unit = processing(output(params))
  println("Task V")
  strategy(printMin, findMin, num)
  strategy(printMax, findMax, num)
  strategy(printMax, findAvg, num)

  //6) Напишите функцию, которая выводит на экран N первых простых чисел
  def printNumber(n: Int = 0): Unit = {
    val col = Seq(n, Int.MaxValue).min
    for (i <- 1 to col) {
      println(s"Value - $i")
    }
  }
  println("Task VI")
  printNumber(10)
}
