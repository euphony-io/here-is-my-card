package com.recipe.android.hereismycard.makeCard

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.recipe.android.hereismycard.MainActivity
import com.recipe.android.hereismycard.MainActivity.Companion.bgList
import com.recipe.android.hereismycard.R
import com.recipe.android.hereismycard.makeCard.adapter.SelectCardBgAdapter
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertEquals
import androidx.test.espresso.matcher.ViewMatchers.hasBackground
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import java.util.regex.Matcher

class MakeCardFragmentTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    private val TEST_ITEM_SIZE = 4

    @Test
    fun testCardViewOnAppLaunch() {
        onView(withId(R.id.cardView)).check(matches(isDisplayed()))
        onView(withId(R.id.layout_card)).check(matches(isDisplayed()))

        onView(withId(R.id.et_name)).check(matches(withHint("Name")))
        onView(withId(R.id.et_number)).check(matches(withHint("010-1234-1234")))
        onView(withId(R.id.et_email)).check(matches(withHint("euphony@gmail.com")))
        onView(withId(R.id.et_address)).check(matches(withHint("서울시 영등포구...")))
    }

    @Test
    fun testClickItem() {
        for (i in 0..TEST_ITEM_SIZE) {
            onView(withId(R.id.rv_select_bg)).perform(
                actionOnItemAtPosition<SelectCardBgAdapter.SelectBgViewHolder>(
                    i,
                    click()
                )
            )
        }
    }
}