package com.example.mazadytask.core.presentation

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * created by Karim Haggagi on 1/6/25
 **/
class AndroidStringProvider @Inject constructor(@ApplicationContext private val context: Context) : StringProvider {
    override fun getString(resId: Int): String {
        return context.getString(resId)
    }

    override fun getString(resId: Int, vararg formatArgs: Any): String {
        return context.getString(resId, *formatArgs)
    }
}