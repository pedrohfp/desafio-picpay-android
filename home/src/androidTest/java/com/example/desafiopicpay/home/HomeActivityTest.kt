package com.example.desafiopicpay.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.desafiopicpay.home.di.homeModule
import com.example.desafiopicpay.home.matchers.RecyclerViewMatcher
import com.example.desafiopicpay.network.di.networkModule
import com.example.desafiopicpay.network.di.url
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers.not
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

    private lateinit var server: MockWebServer

    private lateinit var recyclerViewIdlingResource: RecyclerViewIdlingResource

    @Before
    fun setup() {
        server = MockWebServer()

        server.enqueue(MockResponse().setResponseCode(200).setBody(userListJson))

        startKoin {
            modules(listOf(networkModule, homeModule))
        }

        url = server.url("/").toString()
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun whenListScroll_verifyContactTitleIsNotDisplayed() {

        activityRule.launchActivity(null)

        recyclerViewIdlingResource = RecyclerViewIdlingResource(activityRule.activity)

        IdlingRegistry.getInstance().register(recyclerViewIdlingResource)
        onView(withId(android.R.id.content)).perform(swipeUp())
        IdlingRegistry.getInstance().unregister(recyclerViewIdlingResource)

        onView(withId(R.id.homeTitleTextView)).check(matches(not(isDisplayed())))
    }

    @Test
    fun whenSearchName_verifyListIsFiltered() {
        activityRule.launchActivity(null)

        onView(withId(R.id.search_src_text)).perform(replaceText("Wagner Oliveira"))
        onView(RecyclerViewMatcher(R.id.userListRecyclerView)
            .atPosition(0, R.id.nameTextView))
            .check(matches(withText("Wagner Oliveira")))
    }
}