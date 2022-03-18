package com.example.portfolio

import androidx.test.ext.junit.rules.ActivityScenarioRule
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.testing.HiltAndroidRule

import org.junit.Test

import org.junit.Rule
import org.junit.rules.RuleChain

@HiltViewModel
class ExampleInstrumentedTest {

    private val hiltRule = HiltAndroidRule(this)
    private val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(activityTestRule)!!

    @Test
    fun itemAddedToScreen() {
        on
    }
}