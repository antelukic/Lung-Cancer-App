package com.lukic.presentation.screen.intro.resources

import android.content.res.Resources
import com.lukic.domain.intro.resources.IntroTranslations
import com.lukic.lungcancerapp.R

class IntroTranslationsImpl(private val resources: Resources) : IntroTranslations {

    override fun funFacts(): Array<String> = resources.getStringArray(R.array.intro_fun_facts)
}
