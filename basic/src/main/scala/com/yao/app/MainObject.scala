package com.yao.app

object MainObject {
    def main(args: Array[String]): Unit = {
        println("hello world");
        println(max(3,1))
        println(max2(13,14))
        greet
        args.foreach { x => println(x) }
        
        args.foreach { (x:String) => println(x) }
        
        args.foreach { print }
        
        for(arg <- args)
            println(arg)
         
        // 类型推断，等同val strs:Array[String] = new Array[String](3)
        val strs = new Array[String](3)
        strs(0) = "Hello"
        strs(1) = ","
        strs(2) = "world"
        // strs(0)等同strs.apply(0)  strs(0)=xxx等同strs.update(0,xxx)
        
        // 0 to 2 等同于  (0).to(2)
        for(i<- 0 to 2)
            print(strs(i))
            
        val numNames = Array("1","2","3")
        
        val r = new Rational(2,3)
        println(r*2)
        
        implicit def intToRational(x:Int) = new Rational(x)
        println(2*r)
    }

    def max(x: Int, y: Int): Int = {
        if (x > y) x
        else y
    }

    def max2(x: Int, y: Int) = if (x > y) x else y
    
    def greet()=println("Hello world2")
}
class Rational(n:Int,d:Int){
    require(d!=0)
    private val g = gcd(n,d)
    val number = n/g
    val denom = d/g
    def this(n:Int) = this(n,1)
    def +(that:Rational) = new Rational(this.number*that.denom+this.denom*that.number,this.denom*that.denom)
    def +(i:Int) = new Rational(this.denom*i+this.number,this.denom)
    def *(that:Rational) = new Rational(this.number*that.number,this.denom*that.denom)
    def *(i:Int) = new Rational(this.number*i,this.denom)
    
    override def toString = number +"/"+denom;
    
    private def gcd(a:Int,b:Int):Int = if(b==0) a else gcd(b,a%b)
    
}