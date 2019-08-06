package com.example.desafiopicpay.home

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.desafiopicpay.commonstest.matchers.RecyclerViewMatcher
import com.example.desafiopicpay.home.di.homeModule
import com.example.desafiopicpay.network.di.networkModule
import com.example.desafiopicpay.network.di.url
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers
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
        Espresso.onView(ViewMatchers.withId(android.R.id.content)).perform(ViewActions.swipeUp())
        IdlingRegistry.getInstance().unregister(recyclerViewIdlingResource)

        Espresso.onView(ViewMatchers.withId(R.id.homeTitleTextView))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
    }

    @Test
    fun whenSearchName_verifyListIsFiltered() {
        activityRule.launchActivity(null)

        Espresso.onView(ViewMatchers.withId(R.id.search_src_text))
            .perform(ViewActions.replaceText("Wagner Oliveira"))
        Espresso.onView(
            RecyclerViewMatcher(R.id.userListRecyclerView)
                .atPosition(0, R.id.nameTextView)
        )
            .check(ViewAssertions.matches(ViewMatchers.withText("Wagner Oliveira")))
    }
}
