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
    }

    def max(x: Int, y: Int): Int = {
        if (x > y) x
        else y
    }

    def max2(x: Int, y: Int) = if (x > y) x else y
    
    def greet()=println("Hello world2")
}