package com.example.desafiopicpay.home

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.desafiopicpay.commonstest.robots.RobotsRule
import com.example.desafiopicpay.home.di.homeModule
import com.example.desafiopicpay.network.di.networkModule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

@RunWith(AndroidJUnit4::class)
internal class HomeActivityTest {
    @get:Rule
    val activityRule = ActivityTestRule<HomeActivity>(
        HomeActivity::class.java, true, false)

    @get:Rule
    val homeActivityRobots = RobotsRule(HomeActivityRobots(activityRule))

    @Before
    fun setup() {
        homeActivityRobots.setup()

        startKoin {
            modules(listOf(networkModule, homeModule))
        }
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun whenListScroll_verifyContactTitleIsNotDisplayed() {

        homeActivityRobots {
            mockApi()
            startScreen()
            scrollList()
            verifyContactTitleIsNotDisplayed()
        }
    }

    @Test
    fun whenSearchName_verifyListIsFiltered() {

        val user = "Wagner Oliveira"

        homeActivityRobots {
            mockApi()
            startScreen()
            scrollList()
            typeOnSearchView(user)
            verifyListFiltered(user)
        }
    }
}
