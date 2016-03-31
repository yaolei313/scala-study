package com.yao.app

import java.io.File
import java.io.PrintWriter
import scala.math.Ordered

class Rational(n: Int, d: Int) extends Ordered[Rational] {
    require(d != 0)
    private val g = gcd(n, d)
    val number = n / g
    val denom = d / g
    def this(n: Int) = this(n, 1)
    def +(that: Rational) = new Rational(this.number * that.denom + this.denom * that.number, this.denom * that.denom)
    def +(i: Int) = new Rational(this.denom * i + this.number, this.denom)
    def *(that: Rational) = new Rational(this.number * that.number, this.denom * that.denom)
    def *(i: Int) = new Rational(this.number * i, this.denom)

    override def toString = number + "/" + denom;

    def compare(that: Rational): Int = this.number * that.denom - that.number * this.denom

    // 注意2中equals的区别
    //def equals(that: Rational): Boolean = (this compare that) == 0
    override def equals(that: Any): Boolean = {
        that match {
            case that1: Rational => (this compare that1) == 0
            case _               => false
        }
    }

    private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

}

object MainObject {
    def main(args: Array[String]): Unit = {

        println(if (!args.isEmpty) args(0) else "default args")

        def greet() = println("Hello world2")
        println(greet() == ())

        //println("hello world");
        //println(max(3,1))
        //println(max2(13,14))
        //greet
        args.foreach { (x: String) => println(x) }
        args.foreach { x => println(x) }
        args.foreach { print }
        for (arg <- args)
            println(arg)

        // 类型推断，等同val strs:Array[String] = new Array[String](3)
        val strs = new Array[String](3)
        strs(0) = "Hello"
        strs(1) = ","
        strs(2) = "world"
        // strs(0)等同strs.apply(0)  strs(0)=xxx等同strs.update(0,xxx)

        // 0 to 2 等同于  (0).to(2)
        for (i <- 0 to 2)
            print(strs(i))

        for (i <- 0 until 4)
            println(i)

        val numNames = Array("1", "2", "3")

        val r = new Rational(2, 3)
        println(r * 2)

        implicit def intToRational(x: Int) = new Rational(x)
        println(2 * r)

        /*val pattern = ".*name.*"
        val files = (new java.io.File(".")).listFiles
        for (file <- files)
            println(file.getName)
        for {
            file <- files
            if file.getName.endsWith(".sbt")
            line <- fileLines(file)
            trimed = line.trim
            if trimed.matches(pattern)
        } {
            println(file.getName + ":" + trimed)
            println(file.getPath)
        }*/

        println("--------------")
        //println(fileLinesLength)

        val map = Map("123" -> "hello")
        val b23 = map + ("1234" -> "world")

        /**
         * 借贷模式
         */
        def withPrintWriter(file: File)(op: PrintWriter => Unit) {
            val writer = new PrintWriter(file)
            try {
                op(writer)
            } finally {
                writer.close()
            }
        }

        val file = new File("date.txt");
        withPrintWriter(file) {
            writer => writer.print(new java.util.Date())
        }

        // 传名参数
        def testCallByName(op: () => Boolean) = if (op()) 1 else 2
        def testCallByName2(op: => Boolean) = if (op) 1 else 2

        println(testCallByName(() => 1 > 2))
        println(testCallByName2(1 > 2))

        val t1: Rational = new Rational(10, 1)
        val t2: Rational = new Rational(6, 1)
        val t3: Rational = new Rational(6, 1)
        println(t1 < t2)
        println(t2 == t3)
        println(t2 equals t3)
        //val t2:Int = null null不适用于值类型

        // 标明不正常的终止
        def error(msg: String): Nothing = throw new RuntimeException(msg)

        def divide(x: Int, y: Int): Int = if (y != 0) x / y else error("123")

        def divide2(x: Int, y: Int): Int = if (y != 0) x / y else throw new RuntimeException("123") //抛出异常的类型是Nothing
    }

    def fileLines(file: java.io.File) =
        scala.io.Source.fromFile(file).getLines().toList

    def fileLinesLength() =
        for {
            file <- (new java.io.File(".")).listFiles
            if file.getName.endsWith(".sbt")
            line <- scala.io.Source.fromFile(file).getLines()
            trimed = line.trim()
        } yield { trimed.length }

    def max(x: Int, y: Int): Int = {
        if (x > y) x
        else y
    }

    def max2(x: Int, y: Int) = if (x > y) x else y

}

