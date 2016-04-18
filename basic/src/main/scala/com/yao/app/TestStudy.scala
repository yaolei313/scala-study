/*
 * scala 测试学习
 */
package com.yao.app

import org.scalatest._
import Element._

class ElementSuite extends Suite {
    def testWidth() {
        val ele1 = ele('x', 2, 3)
        assert(ele1.width == 4)
    }
}