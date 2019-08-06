package com.example.desafiopicpay.home.robots

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * The RobotsRule create a approach to use your robotPattern implementation {@see Robot}
 *  by providing a setup for your robot and a lambda ready way to use them
 *
 *  @Rule
 *  val myRobot = RobotsRule(MyRobot())
 *
 *  @Test
 *  fun validate() {
 *      myRobot {
 *          click()
 *      }
 *  }
 *
 */
class RobotsRule<T : Robots>(private val robot: T) : TestRule {

    override fun apply(base: Statement, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {
                base.evaluate()
            }
        }
    }

    fun setup() = robot.setup()

    operator fun invoke(func: T.() -> Unit) = createRobots(func, robot)
}
