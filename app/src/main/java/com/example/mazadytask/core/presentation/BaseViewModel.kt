package com.example.mazadytask.core.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mazadytask.core.domain.DataError
import com.example.mazadytask.core.domain.Error
import com.example.mazadytask.core.domain.toUiText
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

/**
 * created by Karim Haggagi on 1/5/25
 **/
open class BaseViewModel : ViewModel() {
    private val mLoadingState = MutableSharedFlow<Int>()
    open val loadingState: SharedFlow<Int> = mLoadingState.asSharedFlow()

    private val mErrorState = MutableSharedFlow<UiText>()
    open val errorState: SharedFlow<UiText> = mErrorState.asSharedFlow()

    fun showLoading() {
        viewModelScope.launch {
            mLoadingState.emit(1)
        }
    }

    fun hideLoading() {
        viewModelScope.launch {
            mLoadingState.emit(0)
        } }

    fun showError(message: DataError) {
        viewModelScope.launch {
            mErrorState.emit(message.toUiText())
        }
    }
}