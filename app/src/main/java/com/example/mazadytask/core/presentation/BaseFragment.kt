package com.example.mazadytask.core.presentation

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mazadytask.R
import com.example.mazadytask.category.presentation.categories.CategoryAdapter
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import kotlinx.coroutines.launch

/**
 * created by Karim Haggagi on 1/5/25
 **/
abstract class BaseFragment<V : BaseViewModel> : Fragment() {

    private lateinit var mProgressDialog: ProgressDialog

    fun hideLoading() {
        if (mProgressDialog.isShowing) mProgressDialog.cancel()
    }

    fun showLoading() {
        hideLoading()
        mProgressDialog.show()
    }

    abstract fun getViewModel(): V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mProgressDialog = LoadingUtils.showLoadingDialog(requireContext())

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                getViewModel().loadingState.collect {
                    if (it == 1) {
                        showLoading()
                    } else {
                        hideLoading()
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                getViewModel().errorState.collect {
                    Alert.showDialog(requireContext(), it.asString(requireContext()))
                }
            }
        }
    }

}