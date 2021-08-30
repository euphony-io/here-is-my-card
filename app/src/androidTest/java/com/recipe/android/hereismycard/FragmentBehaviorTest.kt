package com.recipe.android.hereismycard

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class FragmentBehaviorTest{
    @get:Rule
    var scenarioRule: ActivityScenarioRule<MainActivity>
            = ActivityScenarioRule(MainActivity::class.java)
    @Test
    fun changeToCardBoxFragment() {
        onView(withId(R.id.btn_box))
            .perform(ViewActions.click())
        onView(withId(R.id.frame_main)).check(matches(isDisplayed()))
    }

    @Test
    fun changeToMakeCardFragment() {
        onView(withId(R.id.btn_edit))
            .perform(ViewActions.click())
        onView(withId(R.id.frame_main)).check(matches(isDisplayed()))
    }
}