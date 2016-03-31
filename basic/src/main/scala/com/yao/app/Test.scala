package com.yao.app

object Test {
    def main(args: Array[String]) {
        val list1 = List("one", "two", "three")
        val list2 = List("hello", "world")

        // zip 以最短长度作为新长度
        val result = for (obj <- list1.zip(list2)) yield {
            obj._1 + obj._2
        }

        println(result.mkString(","))

        class Test(val id: Int,val name: String)

        def getIds[A, B](l: List[A], op: A => B): List[B] = {
            for (o <- l)
                yield op(o)
        }

        val list = List[Test](new Test(1,"hello"), new Test(2,"world"));
        val idList = getIds[Test, Int](list, o => o.id)
        print(idList mkString " ")

    }

}