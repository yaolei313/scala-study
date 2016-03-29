package com.yao.app

import Element._

abstract class Element {
    def content: Array[String]
    def height: Int = content.length
    def width: Int = if (height == 0) 0 else content(0).length

    //def above(that: Element) = new ArrayElement(this.content ++ that.content)
    // 使用工厂类
    def above(that: Element) = ele(this.content ++ that.content)

    def beside(that: Element) = {
        // directive
        /*val content = new Array[String](this.content.length);
        for(i <- 0 until this.content.length)
            content(i) = this.content(i)+that.content(i)
        new ArrayElement(content)*/

        ele(
            for (
                (line1, line2) <- this.content zip that.content
            ) yield line1 + line2)
    }

    def widen(w: Int) = {
        if (w <= width)
            this
        else {
            val left = ele(' ', (w - width) / 2, height)
            val right = ele(' ', w - width - left.width, height)
            left beside this beside right
        }
    }

    def heighten(h: Int) = {
        if (h <= height)
            this
        else {
            val top = ele(' ', width, (h - height) / 2)
            val buttom = ele(' ', width, h - height - top.height)
            top above this above buttom
        }
    }

    override def toString = content mkString "\n"
}

object Element {
    private class ArrayElement(val content: Array[String]) extends Element

    private class LineElement2(s: String) extends ArrayElement(Array(s))

    private class LineElement(s: String) extends Element {
        def content = Array(s)
    }

    private class UniformElement(ch: Char, override val width: Int, override val height: Int) extends Element {
        private val line = ch.toString * width
        def content = Array.fill(height)(line)
    }
    // 必须带:Element，不然会编译错误
    // For some reason it would rather fail the compile than infer a less specific but public type. Declare the elem methods with return type.
    // The type is private, so it is not visible outside its defining scope.
    def ele(content: Array[String]): Element = new ArrayElement(content)
    def ele(line: String): Element = new LineElement(line)
    def ele(ch: Char, width: Int, height: Int): Element = new UniformElement(ch, width, height)
}

object Main extends App {

    object Direction extends Enumeration {
        type Direction = Value
        val RIGHT, BOTTOM, LEFT, TOP = Value
    }
    import Direction._
    
    val param1 = 8
    val direction = Direction.RIGHT
    
    def sprial(w: Int, direction: Int) : Element = {
        if(w ==1)
            ele("+")
        else {
            val sp = sprial(w-1, (direction+3)%4)
            ele(" ")
        }
    }

    def sprial2(w: Int, direct: Direction): Element = {
        if (w == 1)
            ele("+")
        else {
            //val sp = sprial(w-1,Direction.,)
            ele(" ")
        }
    }
}