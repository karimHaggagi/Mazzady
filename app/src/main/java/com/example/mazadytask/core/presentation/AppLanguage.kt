package com.example.mazadytask.core.presentation

import android.annotation.SuppressLint
import java.util.Locale

/**
 * created by Karim Haggagi on 1/5/25
 **/

enum class AppLanguage(private val language: String) {
    ENGLISH("en"),
    ARABIC("ar");

    companion object {
        @SuppressLint("ConstantLocale")
        val appLanguage = Locale.getDefault().language
        fun getLanguage(): AppLanguage {
            return entries.find { it.language == appLanguage } ?: ENGLISH
        }
    }
}
