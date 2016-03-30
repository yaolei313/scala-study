package com.yao.app

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

object PhilosphicalMain extends App {
    
}