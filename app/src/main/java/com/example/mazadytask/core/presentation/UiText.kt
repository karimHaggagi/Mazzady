package com.example.mazadytask.core.presentation

import android.content.Context
import androidx.annotation.StringRes
import com.example.mazadytask.R

/**
 * created by Karim Haggagi on 1/5/25
 **/
sealed interface UiText {
    data class DynamicString(val value: String): UiText
    class StringResourceId(
        @StringRes val id: Int,
        val args: Array<Any> = arrayOf()
    ): UiText

    fun asString(context: Context): String {
        return when(this) {
            is DynamicString -> value
            is StringResourceId -> context.getString(id, *args)
        }
    }
}