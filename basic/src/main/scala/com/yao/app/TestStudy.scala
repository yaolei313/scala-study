/*
 * scala 测试学习
 */
package com.yao.app

import org.scalatest.Finders
import org.scalatest.FlatSpec
import org.scalatest.FreeSpec
import org.scalatest.FunSpec
import org.scalatest.FunSuite
import org.scalatest.Matchers
import org.scalatest.PropSpec
import org.scalatest.Suite
import org.scalatest.WordSpec
import org.scalatest.prop.TableDrivenPropertyChecks

import Element.ele

class ElementSuite extends Suite {
    def testWidth() {
        val ele1 = ele('x', 2, 3)
        assert(ele1.width == 2)
        assert(ele1.width == 2, "")
    }

    def testWidth2() {
        // expect xx bu xx 效果
        assertResult(2) {
            1 + 2
        }
    }

    def testWidth3() {
        // 期望抛出异常
        intercept[IllegalArgumentException] {
            ele('x', -2, 3)
        }
    }

}

class FunTest extends FunSuite {
    // 第一个参数是testname，第二个参数是测试函数
    test("1 An empty set should have size 0") {
        assert(Set.empty.size == 0)
    }

    test("2 NoSuchElementException") {
        intercept[NoSuchElementException] {
            Set.empty.head
        }
    }
}

// 也支持junit3，4，testng，此处只使用scalatest

// 规格测试 behavior-driven development BDD
class SetSpec extends FlatSpec {
    "An empty set" should "have size 0" in {
        assert(Set.empty.size == 0)
    }

    it should "produce NoSuchElementException when head is invoked" in {
        intercept[NoSuchElementException] {
            Set.empty.head
        }
    }
}

class SetSpec2 extends FunSpec {
    describe("A Set") {
        describe("when empty") {
            it("should have size 0") {
                assert(Set.empty.size == 0)
            }
            it("produce NoSuchElementException when head is invoked") {
                intercept[NoSuchElementException] {
                    Set.empty.head
                }
            }
        }
        describe("when not empty") {
            it("should have size > 0") {
                val set1 = Set(1, 2, 3)
                assert(set1.size > 0)
            }
        }
    }
}

class SetSpec3 extends WordSpec {
    "A set" when {
        "empty" should {
            "have size 0" in {
                assert(Set.empty.size == 0)
            }
            
            "produce NoSuchElementException when head is invoked" in {
                intercept[NoSuchElementException] {
                    Set.empty.head
                }
            }
        }
    }
}

class SetSpec4 extends FreeSpec {
    "A set" - {
        "empty" - {
            "have size 0" in {
                assert(Set.empty.size == 0)
            }
            
            "produce NoSuchElementException when head is invoked" in {
                intercept[NoSuchElementException] {
                    Set.empty.head
                }
            }
        }
    }
}

class SetSpec5 extends PropSpec with TableDrivenPropertyChecks with Matchers{
    //val examples = Table("set",BitSet.empty,HashSet.empty,TreeSet.empty)
}


