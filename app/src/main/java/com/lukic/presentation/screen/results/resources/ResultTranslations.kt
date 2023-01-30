package com.lukic.presentation.screen.results.resources

import android.content.res.Resources
import com.lukic.lungcancerapp.R

private const val BAD_RESULTS_LINK = "https://www.nhs.uk/conditions/lung-cancer/treatment/"
private const val GOOD_RESULTS_LINK = "https://www.cdc.gov/cancer/lung/basic_info/prevention.htm"

interface ResultTranslations {

    fun animation(isSick: Boolean): Int

    fun title(isSick: Boolean): String

    fun link(isSick: Boolean): String

    fun body(isSick: Boolean): String
}

class ResultTranslationsImpl(private val resources: Resources) : ResultTranslations {

    override fun animation(isSick: Boolean): Int = if (isSick) {
        R.raw.doctors
    } else {
        R.raw.good_feedback
    }

    override fun body(isSick: Boolean): String = if (isSick) {
        resources.getString(R.string.bad_result_body)
    } else {
        resources.getString(R.string.good_result_body)
    }

    override fun link(isSick: Boolean): String = if (isSick) {
        BAD_RESULTS_LINK
    } else {
        GOOD_RESULTS_LINK
    }

    override fun title(isSick: Boolean): String = if (isSick) {
        resources.getString(R.string.sick_title)
    } else {
        resources.getString(R.string.good_results)
    }
}
