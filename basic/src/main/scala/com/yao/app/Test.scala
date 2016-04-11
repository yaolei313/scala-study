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

        // 对象list字段提取 投影操作
        class Test(val id: Int, val name: String)

        def getIds[A, B](l: List[A], op: A => B): List[B] = {
            for (o <- l)
                yield op(o)
        }

        val list = List[Test](new Test(1, "hello"), new Test(2, "world"));
        val idList = getIds[Test, Int](list, o => o.id)
        print(idList mkString " ")

        // 测试与断言
        testEnsuring(8)
    }

    def testEnsuring(i: Int): Int = {
        //assert(i==10)
        if (i > 10) i else 10 ensuring (i < 10)
    } ensuring (_ < 10) // 不能换行，表示对本方法的返回结果进行断言

}