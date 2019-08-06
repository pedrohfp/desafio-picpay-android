package com.example.desafiopicpay.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.example.desafiopicpay.home.matchers.RecyclerViewMatcher
import com.example.desafiopicpay.home.robots.Robots
import com.example.desafiopicpay.network.di.url
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.not

internal class HomeActivityRobots(private val rule: ActivityTestRule<HomeActivity>) : Robots {
    private lateinit var server: MockWebServer

    private lateinit var recyclerViewIdlingResource: RecyclerViewIdlingResource

    override fun setup() {
        server = MockWebServer()

        url = server.url("/").toString()
    }

    fun mockApi() {
        server.enqueue(MockResponse().setResponseCode(200).setBody(userListJson))
    }

    fun startScreen() {
        rule.launchActivity(null)
    }

    fun scrollList() {
        recyclerViewIdlingResource = RecyclerViewIdlingResource(rule.activity)

        IdlingRegistry.getInstance().register(recyclerViewIdlingResource)
        onView(withId(android.R.id.content)).perform(swipeUp())
        IdlingRegistry.getInstance().unregister(recyclerViewIdlingResource)
    }

    fun verifyContactTitleIsNotDisplayed() {
        onView(withId(com.example.desafiopicpay.home.R.id.homeTitleTextView))
            .check(matches(not(isDisplayed())))
    }

    fun typeOnSearchView(text: String) {
        onView(withId(R.id.search_src_text)).perform(
            replaceText(text)
        )
    }

    fun verifyListFiltered(text: String) {
        onView(
            RecyclerViewMatcher(R.id.userListRecyclerView)
                .atPosition(0, R.id.nameTextView))
            .check(matches(withText(text)))
    }
}
