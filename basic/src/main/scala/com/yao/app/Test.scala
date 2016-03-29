package com.yao.app

object Test {
    def main(args: Array[String]) {
        val list1 = List("one","two","three")
        val list2 = List("hello","world")
        
        // zip 以最短长度作为新长度
        val result = for(obj <- list1.zip(list2)) yield{
            obj._1+obj._2
        }
        
        println(result.mkString(","))
    }
}