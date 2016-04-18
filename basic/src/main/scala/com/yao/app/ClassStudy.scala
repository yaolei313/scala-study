/*
 * 类和伴生对象
 */
package com.yao.app

import Element._

abstract class Element {
    def content: Array[String]
    def height: Int = content.length
    def width: Int = if (height == 0) 0 else content(0).length

    //def above(that: Element) = new ArrayElement(this.content ++ that.content)
    // 使用工厂类
    def above(that: Element): Element = {
        val new1 = this widen that.width
        val new2 = that widen this.width
        assert(new1.width == new2.width)
        ele(new1.content ++ new2.content)
    }

    def beside(that: Element): Element = {
        // directive
        /*val content = new Array[String](this.content.length);
        for(i <- 0 until this.content.length)
            content(i) = this.content(i)+that.content(i)
        new ArrayElement(content)*/
        val new1 = this heighten that.height
        val new2 = that heighten this.height
        ele(
            for (
                (line1, line2) <- new1.content zip new2.content
            ) yield line1 + line2)
    }

    def widen(w: Int): Element = {
        if (w <= width)
            this
        else {
            val left = ele(' ', (w - width) / 2, height)
            val right = ele(' ', w - width - left.width, height)
            left beside this beside right
        }
    } ensuring (w <= _.width)

    def heighten(h: Int): Element = {
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
    val corner = ele("+")
    val space = ele(" ")

    def sprial(w: Int, direction: Int): Element = {
        if (w == 1)
            ele("+")
        else {
            val sp = sprial(w - 1, (direction + 3) % 4)
            val verticalBar = ele('|', 1, sp.height)
            val horizontalBar = ele('-', sp.width, 1)
            if (direction == 0) // right
                (corner beside horizontalBar) above (sp beside space)
            else if (direction == 1) // down
                (sp above space) beside (corner above verticalBar)
            else if (direction == 2) // left
                (space beside sp) above (horizontalBar beside corner)
            else // up
                (verticalBar above corner) beside (space above sp)
        }
    }

    println(sprial(8, 0))

}