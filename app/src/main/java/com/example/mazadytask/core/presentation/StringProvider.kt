package com.example.mazadytask.core.presentation

/**
 * created by Karim Haggagi on 1/6/25
 **/
interface StringProvider {
    fun getString(resId: Int): String
    fun getString(resId: Int, vararg formatArgs: Any): String
}