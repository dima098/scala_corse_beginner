/**
  * Created by ugrin on 19.10.16.
  */
object Ugrin extends App{
//  1) Написать функцию поиска минимума из списка параметров функции ( params: Seq[Double] )
  def findMin(args: Double*): Double = {
    if (args.isEmpty) {
      0
    } else {
      var tempMin: Double = args.head
      for (p <- args.tail if tempMin > p) {
          tempMin = p
      }
      tempMin
    }
  }
  println("findMin --------")
  println(findMin(1, 2, 4, 2, -1))
  println(findMin())

//  2) Написать функцию поиска максимума из списка параметров функции ( params: Seq[Double] )
  def findMax(args: Double*): Double = {
    if (args.isEmpty) {
      0
    } else {
      var tempMin: Double = args.head
      for (p <- args.tail if tempMin < p) {
          tempMin = p
      }
      tempMin
    }
  }
  println("findMax ----------")
  println(findMax(1, 2, 4, 2, -1))
  println(findMax())

//  3) Написать функцию поиска среднего арифметического из списка параметров функции ( params: Seq[Double] )
  def findMiddle(args: Double*): Double = {
    if (args.isEmpty) {
      0
    } else {
      var s =   .0
      for (p <- args) {
        s += p
      }
      s / args.length
    }
  }
  println("findMiddle ----------")
  println(findMiddle(1, 2, 4, 2, -1))
  println(findMiddle())

//4) Написать несколько функций (Double) => Unit которые выводят на экран значение в какой то особой форме (см. пример в лекции )
  def write (arg: Double): Unit = {
    println(s"write: $arg")
  }
  def say(arg: Double): Unit = {
    println(s"say: $arg")
  }

  write(13123123)
  say(findMax(1,2,3,4,5,2234234,234,234234,234,2))


//  5) И теперь реализуем паттерн Стратегия
//  Создайте функцию которая принимает функцию обработки, функцию вывода и список параметров и
//    выводит на экран с помощью функции вывода результат применения функции обработки к списку параметров
//    Предусмотрите значения по умолчанию


  def strategy(
              echo: (Double) => Unit,
              transformer:(Seq[Double]) => Double,
              args: Double*

              ):Unit = echo(transformer(args))

  println("strategy: --------")
  strategy(say, findMin, 1,2,3,4,5,2234234,234,234234,234,2)



//   6) Напишите функцию, которая выводит на экран N первых простых чисел

  def printPrime(n: Int) = {
    def isP (n: Int): Boolean = {
      for {
        i <- 2 to n/2 if n > 1 && n % i == 0
      } {
          return false
      }
      true
    }

    for (i <- 2 to n) {
      if(isP(i)) {
        print(s"$i ")
      }
    }
  }
  printPrime(50)



}
