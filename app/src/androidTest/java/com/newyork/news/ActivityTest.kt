package com.newyork.news

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.newyork.news.ui.news.NewsListActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class ActivityTest {

    @get:Rule
    val activityRule= ActivityScenarioRule(NewsListActivity::class.java)

    @Test
     fun test_isListVisble_onAppLunch() {
        runBlocking {


            delay(2000)
            onView(withId(R.id.rv_news)).check(matches(isDisplayed()))
        }
    }


    @Test
    fun test_selectListItem_isDetalisActivityVisble() {
        runBlocking {
            delay(3000)

            onView(withId(R.id.rv_news)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
            onView(withId(R.id.tv_abstract)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun test_back_toListAcitivity() {
        runBlocking {
            delay(3000)

            onView(withId(R.id.rv_news)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
            onView(withId(R.id.tv_abstract)).check(matches(isDisplayed()))

            pressBack()

            onView(withId(R.id.rv_news)).check(matches(isDisplayed()))
        }
    }




}