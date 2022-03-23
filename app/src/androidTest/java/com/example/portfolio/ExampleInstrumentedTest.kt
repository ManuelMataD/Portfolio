package com.example.portfolio

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Matchers.containsString

import org.junit.Test

import org.junit.Rule
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@HiltAndroidTest
@RunWith(JUnit4::class)
class ExampleInstrumentedTest {

    private val hiltRule = HiltAndroidRule(this)
    private val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(activityTestRule)!!

    @Test
    fun itemAddedToScreen() {
        // TODO: Test of items
    }
}