package com.lukic.presentation.screen.intro

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeLeft
import androidx.compose.ui.test.swipeRight
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lukic.presentation.screen.intro.IntroScreenTestTags.PAGER
import com.lukic.presentation.screen.intro.IntroScreenTestTags.PAGER_INDICATOR
import com.lukic.presentation.screen.intro.IntroScreenTestTags.START_BUTTON
import com.lukic.presentation.screen.intro.IntroScreenTestTags.TITLE
import com.lukic.presentation.screen.intro.resources.IntroTranslations
import com.lukic.presentation.screen.intro.resources.IntroTranslationsImpl
import com.lukic.presentation.screen.userdetails.InputInfoScreenTestTags
import com.lukic.presentation.ui.compose.theme.AppTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IntroScreenKtTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    private lateinit var translations: IntroTranslations

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>().applicationContext
        translations = IntroTranslationsImpl(resources = context.resources)
        composeTestRule.setContent {
            AppTheme {
                IntroScreen(
                    pageContent = translations.funFacts().toList(),
                    onStartClick = {}
                )
            }
        }
    }

    @Test
    fun verifyAllViewsExist() {
        composeTestRule.onNodeWithTag(TITLE).assertExists()
        composeTestRule.onNodeWithTag(PAGER).assertExists()
        composeTestRule.onNodeWithTag(PAGER_INDICATOR).assertExists()
        composeTestRule.onNodeWithTag(START_BUTTON).assertExists()
    }

    @Test
    fun verifyFunFactContentOnAllPages() {
        for (page in 0..4) {
            verifyFunFactContent(page)
        }
    }

    private fun verifyFunFactContent(page: Int) {
        composeTestRule.onRoot().performTouchInput {
            for (i in 0..page) {
                if (i != 0)
                    swipeLeft()
            }
        }
        composeTestRule.onAllNodesWithText(translations.funFacts().toList()[page]).onFirst().assertExists()
    }

    @Test
    fun verifyLastPageNotSwipeAbleToRight() {
        composeTestRule.onRoot().performTouchInput {
            for (i in 0..10) {
                swipeLeft()
            }
        }
        composeTestRule.onAllNodesWithText(translations.funFacts().toList().last()).onFirst().assertExists()
    }

    @Test
    fun verifyFirstPageNotSwipeAbleToLeft() {
        composeTestRule.onRoot().performTouchInput {
            for (i in 0..10) {
                swipeRight()
            }
        }
        composeTestRule.onAllNodesWithText(translations.funFacts().toList().first()).onFirst().assertExists()
    }

    @Test
    fun verifyStartButtonLeadsToInputInfo() {
        composeTestRule.onNodeWithTag(START_BUTTON).performClick()
        composeTestRule.onNodeWithTag(InputInfoScreenTestTags.TITLE).assertExists()
    }
}
