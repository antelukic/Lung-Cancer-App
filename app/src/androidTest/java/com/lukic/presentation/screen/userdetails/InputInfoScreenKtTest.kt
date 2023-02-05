package com.lukic.presentation.screen.userdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lukic.presentation.screen.userdetails.InputInfoScreenTestTags.ARROW_BACK
import com.lukic.presentation.ui.compose.theme.AppTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InputInfoScreenKtTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            AppTheme {
                InputInfoScreen(
                    content = Content.Default,
                    onGenderClick = {},
                    onYesClick = {},
                    onNoClick = {},
                    onAgeSelected = {},
                    onArrowBack = {},
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                )
            }
        }
    }

    @Test
    fun verifyAllViewsExist() {
        composeTestRule.onNodeWithTag(InputInfoScreenTestTags.TITLE).assertExists()
        composeTestRule.onNodeWithTag(InputInfoScreenTestTags.PAGER).assertExists()
        composeTestRule.onNodeWithTag(InputInfoScreenTestTags.HORIZONTAL_PAGER_INDICATOR).assertExists()
    }

    @Test
    fun verifyArrowBackNotVisibleOnFirstQuestion() {
        composeTestRule.onNodeWithTag(testTag = ARROW_BACK).assertDoesNotExist()
    }
}
