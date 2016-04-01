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
        if (x >= 0) super.put(x + 1)
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
}