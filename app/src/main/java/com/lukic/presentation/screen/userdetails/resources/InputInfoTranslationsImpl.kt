package com.lukic.presentation.screen.userdetails.resources

import android.content.res.Resources
import com.lukic.lungcancerapp.R

class InputInfoTranslationsImpl(private val resources: Resources) : InputInfoTranslations {

    override fun inputInfoQuestion(step: Int): String = resources.getStringArray(R.array.input_details_questions)[step]
}
