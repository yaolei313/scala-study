/*
 * 特质
 */
package com.yao.app

import scala.collection.mutable.ArrayBuffer

abstract class IntQueue {
    def get: Int
    def put(x: Int)
}

// extens IntQueue意为这该特质只能混入IntQueue
// 堆叠改动必须有abstract override标志
trait Doubling extends IntQueue {
    abstract override def put(x: Int) { super.put(x * 2) }
}

trait Incrementing extends IntQueue {
    abstract override def put(x: Int) { super.put(x + 1) }
}

trait Filtering extends IntQueue {
    abstract override def put(x: Int) {
        if (x >= 0) super.put(x)
    }
}

// 此处不能加上with Doubling，因为会提示ovveride put，即特质只能改变已有类，此处就是必须先有的已有类
class BasicIntQueue extends IntQueue {
    private val buf = new ArrayBuffer[Int]
    def get() = buf.remove(0)
    def put(x: Int) { buf.+=(x) }
}

class MyIntQueue extends BasicIntQueue with Doubling

object TraintMain extends App {
    val queue = new MyIntQueue
    queue.put(10)
    queue.put(2)
    println(queue.get())
    println(queue.get())
    
    val q2 = new BasicIntQueue with Filtering with Incrementing with Doubling
    q2.put(11)
    q2.put(-1)
    println(q2.get())
}

// 另一个特质例子
trait Philosphical {
    def philosphize() {
        println("I consume memory, therefore I am!")
    }
}
// 特质 使用extends或with混入
class Frog2 extends Philosphical {
    override def toString  = "green"
}

// 特质 可以声明 字段和方法，但不能有任何类参数，可规避
class Annimal

trait HasLegs

class Frog extends Annimal with HasLegs with Philosphical {
    override def toString  = "green"
    override def philosphize() {
        println("It ain't easy being "+ toString +"!")
    }
}