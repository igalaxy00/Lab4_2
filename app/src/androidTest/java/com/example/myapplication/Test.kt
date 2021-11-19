package com.example.myapplication

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import android.content.pm.ActivityInfo
import android.widget.Button
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.matcher.ViewMatchers.*

import org.junit.Assert

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class Test {

    fun firstExist(){
        onView(withId(R.id.activity_main)).check(matches(isDisplayed()))

        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(doesNotExist())
        onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToThird)).check(doesNotExist())
    }

    fun secondExist(){
        onView(withId(R.id.activity_main)).check(matches(isDisplayed()))
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).check(doesNotExist())
        onView(withId(R.id.bnToThird)).check(matches(isDisplayed()))
    }

    fun thirdExist(){
        onView(withId(R.id.activity_main)).check(matches(isDisplayed()))
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToThird)).check(doesNotExist())
    }

    fun aboutExist(){
        onView(withId(R.id.activity_main)).check(doesNotExist())
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAbout)).check(matches(isDisplayed()))
    }


    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun testNavigationUp(){
        firstExist()
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).check(matches(isDisplayed()))
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        firstExist()
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).check(matches(isDisplayed()))
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        secondExist()
        onView(withId(R.id.bnToThird)).perform(click())
        openAbout()
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).check(matches(isDisplayed()))
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).check(matches(isDisplayed()))
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())
        firstExist()
    }

    @Test
    fun firstFragmentTest() {//my test

        //onView(withId(R.id.text_simple)).check(matches(withText("Hello")))

        firstExist()

        //1f - 2f - 1f
        onView(withId(R.id.bnToSecond)).perform(click())

        secondExist()

        pressBack()

        firstExist()

        //1f - about - 1f
        openAbout()

        aboutExist()

        pressBack()

        firstExist()
    }

    @Test
    fun secondFragmentTest() {
        //imitating user activity: getting to the 2nd fragment
        onView(withId(R.id.bnToSecond)).perform(click())

        secondExist()

        //2f - 1f - 2f
        onView(withId(R.id.bnToFirst)).perform(click())

        firstExist()

        onView(withId(R.id.bnToSecond)).perform(click())

        secondExist()


        //2f - 3f - 2f
        onView(withId(R.id.bnToThird)).perform(click())

        thirdExist()

        pressBack()

        secondExist()

        //2f - about - 2f
        openAbout()

        aboutExist()

        pressBack()

        secondExist()
    }


    @Test
    fun thirdFragmentTest() {
        //imitating user activity: getting to the 3rd fragment
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())

        thirdExist()

        //3f - 1f - 3f
        onView(withId(R.id.bnToFirst)).perform(click())

        firstExist()

        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())

        thirdExist()

        //3f - 2f - 3f
        onView(withId(R.id.bnToSecond)).perform(click())

        secondExist()


        onView(withId(R.id.bnToThird)).perform(click())

        thirdExist()

        //3f - about - 3f
        openAbout()

        aboutExist()

        pressBack()

        thirdExist()
    }


    @Test
    fun testNavigation() {

        firstExist()

        openAbout()

        aboutExist()

        pressBack()

        onView(withId(R.id.bnToSecond)).perform(click())

        secondExist()

        openAbout()

        aboutExist()

        pressBack()

        onView(withId(R.id.bnToThird)).perform(click())

        thirdExist()


        openAbout()

        aboutExist()

    }





    @Test
    fun testAbout() {
        launchActivity<MainActivity>()
        openAbout()
        Espresso.onView(ViewMatchers.withId(R.id.activity_about))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testBackStack() {
        // fill backstack
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withId(R.id.bnToFirst)).perform(click())
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToFirst)).perform(click())
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())

        openAbout()
        aboutExist()

        pressBack()

        thirdExist()

        openAbout()
        aboutExist()
        pressBack()


        pressBack()
        secondExist()

        openAbout()
        aboutExist()
        pressBack()


        pressBack()
        firstExist()

        openAbout()
        aboutExist()
        pressBack()


        pressBackUnconditionally()
        Assert.assertTrue(activityRule.scenario.state.isAtLeast(Lifecycle.State.DESTROYED))
    }

    @Test
    fun checkRotationScreen() {

        // first fragment
        firstExist()

        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        firstExist()

        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        onView(withId(R.id.bnToSecond)).perform(click())

        // second fragment
        secondExist()

        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        secondExist()

        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        onView(withId(R.id.bnToThird)).perform(click())

        // third fragment
        thirdExist()

        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        thirdExist()

        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        openAbout()

        aboutExist()

        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAbout)).check(matches(isDisplayed()))

        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

    }




}