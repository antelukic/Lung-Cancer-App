package com.lukic.presentation.screen.results

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lukic.presentation.screen.results.ResultScreenTestTags.ANIMATION
import com.lukic.presentation.screen.results.ResultScreenTestTags.BODY
import com.lukic.presentation.screen.results.ResultScreenTestTags.LEARN_MORE_BUTTON
import com.lukic.presentation.screen.results.ResultScreenTestTags.LOADER
import com.lukic.presentation.screen.results.ResultScreenTestTags.TITLE
import com.lukic.presentation.screen.results.resources.ResultTranslations
import com.lukic.presentation.screen.results.resources.ResultTranslationsImpl
import com.lukic.presentation.ui.compose.theme.AppTheme
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ResultScreenKtTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    private lateinit var resultTranslations: ResultTranslations
    private var isSickState = MutableStateFlow(false)
    private var isLoading = MutableStateFlow(false)

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>().applicationContext
        resultTranslations = ResultTranslationsImpl(context.resources)
        composeTestRule.setContent {
            val isSick = isSickState.collectAsState().value
            AppTheme {
                val content = with(resultTranslations) {
                    Content(
                        animationId = animation(isSick),
                        title = title(isSick),
                        link = link(isSick),
                        body = body(isSick),
                        isLoading = isLoading.collectAsState().value
                    )
                }
                ResultScreen(
                    contentState = content,
                    onLearnMoreClick = {}
                )
            }
        }
    }

    @Test
    fun verifyAllViewsExist() {
        composeTestRule.onNodeWithTag(TITLE).assertExists()
        composeTestRule.onNodeWithTag(BODY).assertExists()
        composeTestRule.onNodeWithTag(ANIMATION).assertExists()
        composeTestRule.onNodeWithTag(LEARN_MORE_BUTTON).assertExists()
    }

    @Test
    fun verifyLoaderExist() {
        isLoading.value = true
        composeTestRule.onNodeWithTag(LOADER).assertExists()
    }

    @Test
    fun verifyRightDataShownWhenSick() {
        isSickState.value = true
        isLoading.value = false
        composeTestRule.onNodeWithTag(TITLE).assertTextContains(resultTranslations.title(true))
        composeTestRule.onNodeWithTag(BODY).assertTextContains(resultTranslations.body(true))
    }

    @Test
    fun verifyRightDataShownWhenNotSick() {
        isSickState.value = false
        isLoading.value = false
        composeTestRule.onNodeWithTag(TITLE).assertTextContains(resultTranslations.title(false))
        composeTestRule.onNodeWithTag(BODY).assertTextContains(resultTranslations.body(false))
    }
}
