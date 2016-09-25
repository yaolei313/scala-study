package com.yao.app

object ReadScript {
    def readLines() =
        for {
            line <- scala.io.Source.fromFile(new java.io.File("/Users/yaolei/Documents/temp.txt")).getLines()
            if line != ""
        } yield { "'" + line + "'" }

    def getMisStatus(dealId: String) = {
        val prefix = "http://st.deal.sankuai.com/api/output/mis/";
        try {

        } catch {
            case e: Exception => println(e)
        }
    }

    def main(args: Array[String]): Unit = {
        
        val lines = readLines()
        val result = for (dealId <- lines if getMisStatus(dealId) == 32) yield dealId

        println("end")
    }

}