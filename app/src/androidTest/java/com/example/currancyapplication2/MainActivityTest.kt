package com.example.currancyapplication2


import android.content.ContentValues.TAG
import android.os.SystemClock
import android.util.Log
import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import org.hamcrest.CoreMatchers.*
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest : TestCase() {


    @Test
    fun isDisplayedList() {
        ActivityScenario.launch(MainActivity::class.java)
        //1:ListView vith currencies load and displayed
        Espresso.onView(withId(R.id.listView))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        //3
        //onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(11).
        //perform(click())
    }

    @Test
    fun checkButtonUptClick() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.updateCurrancyBtn)).perform(click());
    }

    @Test
    fun ElementVisible() {
        ActivityScenario.launch(MainActivity::class.java)
        //5 element is Visible
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(5)
    }

    @Test//(flake)
    fun findUsd() {
        val result = 13.4885

        ActivityScenario.launch(MainActivity::class.java)
        SystemClock.sleep(1500);

        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(10).perform(scrollTo(), click())
        onView(withId(R.id.inputConvertibleValue)).perform(typeText(1000.toString()))
        onView(withId(R.id.inputConvertibleValue)).perform(closeSoftKeyboard());

        onView(withId(R.id.convertButton)).check(matches(isDisplayed()))
        onView(withId(R.id.convertButton)).perform(click())

        //assert result
        onView(withId(R.id.afterConversionValue)).check(matches(isDisplayed()))

        onView(withId(R.id.afterConversionValue)).check(matches(withText(result.toString())))
    }
}