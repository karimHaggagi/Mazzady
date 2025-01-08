package com.example.mazadytask.core.data

import javax.inject.Qualifier

/**
 * created by Karim Haggagi on 11/10/24
 **/

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainDispatcher
