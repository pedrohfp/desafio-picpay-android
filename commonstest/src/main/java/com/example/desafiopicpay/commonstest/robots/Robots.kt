package br.com.odete.commonstest.test.robots

/**
 * Used by {@see RobotsRule} to create a Robots Pattern class
 */
interface Robots {

    /**
     * Prepare robots to be used, can be used in test @Before like:
     *
     * @Before
     * fun class setup() {
     *  robots.setup()
     * }
     */
    fun setup()
}