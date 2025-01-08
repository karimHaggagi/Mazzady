package com.example.mazadytask

import com.example.mazadytask.core.presentation.StringProvider

/**
 * created by Karim Haggagi on 1/8/25
 **/
class FakeStringProvider : StringProvider {
    override fun getString(resId: Int): String {
        return "Server error"
    }

    override fun getString(resId: Int, vararg formatArgs: Any): String {
        return "Server error"
    }
}
