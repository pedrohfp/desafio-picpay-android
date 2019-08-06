package br.com.odete.commonstest.test.robots

/**
 * From a {@see Robot} implementation create a extension function
 * to be executed the robots methods as lambdas
 */
fun <T> createRobots(func: T.() -> Unit, robot: T) = robot.apply { func() }