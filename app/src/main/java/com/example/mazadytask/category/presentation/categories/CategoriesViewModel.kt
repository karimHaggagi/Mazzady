package com.example.mazadytask.category.presentation.categories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mazadytask.R
import com.example.mazadytask.category.domain.model.CategoryModel
import com.example.mazadytask.category.domain.model.CategoryOptionsModel
import com.example.mazadytask.category.domain.model.ChildrenModel
import com.example.mazadytask.category.domain.repository.CategoriesRepository
import com.example.mazadytask.core.domain.onError
import com.example.mazadytask.core.domain.onSuccess
import com.example.mazadytask.core.presentation.BaseViewModel
import com.example.mazadytask.core.presentation.StringProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * created by Karim Haggagi on 1/5/25
 **/
@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val repository: CategoriesRepository,
    private val stringProvider: StringProvider
) :
    BaseViewModel() {

    private val _categoriesFlow = MutableStateFlow<List<CategoryModel>>(emptyList())
    val categoriesFlow = _categoriesFlow.onStart {
        showLoading()
        getAllCategories()
    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _categoriesFlow.value
        )

    private val selectedMainCategory: MutableStateFlow<CategoryModel?> = MutableStateFlow(null)
    val subCategoryFlow: StateFlow<List<ChildrenModel>?> = selectedMainCategory.map {
        it?.children
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        selectedMainCategory.value?.children
    )

    private val _categoryPropertiesFlow = MutableStateFlow<List<PropertiesDropDownUi>>(emptyList())
    val categoryPropertiesFlow = _categoryPropertiesFlow.asStateFlow()

    private var selectedSubCategory: ChildrenModel? = null

    fun getAllCategories() = viewModelScope.launch {
        repository.getAllRepositories()
            .onSuccess { response ->
                hideLoading()
                _categoriesFlow.emit(response)
            }
            .onError {
                hideLoading()
                showError(it)
            }
    }

    fun setSelectedCategory(position: Int) {
        viewModelScope.launch {
            val selectedCategory = categoriesFlow.value[position]
            selectedMainCategory.emit(selectedCategory)
            _categoryPropertiesFlow.emit(emptyList())

        }
    }

    fun setSelectedSubCategory(position: Int) {
        viewModelScope.launch {
            Log.d("TAG", "setSelectedSubCategory: ${subCategoryFlow.value?.get(position)}")
            _categoryPropertiesFlow.emit(emptyList())
            subCategoryFlow.value?.get(position)?.let { subCategoryItem ->
                selectedSubCategory = subCategoryItem
                showLoading()
                repository.getCategoryProperties(subCategoryItem.id)
                    .onSuccess { response ->
                        hideLoading()
                        _categoryPropertiesFlow.emit(response.map {
                            it.asPropertiesDropDownUi(
                                stringProvider.getString(R.string.other)
                            )
                        })
                    }
                    .onError {
                        hideLoading()
                        showError(it)
                    }
            }

        }

    }

    fun setSelectedOption(selectedPosition: Int, item: PropertiesDropDownUi) {
        val option = item.options[selectedPosition]
        if (!option.hasChild) {
            return
        }
        viewModelScope.launch {
            showLoading()
            repository.getCategoryOptions(optionId = option.optionId)
                .onSuccess { response ->
                    handleOptionSuccess(response, item)
                }
                .onError {
                    hideLoading()
                    showError(it)
                }
        }
    }


    private fun handleOptionSuccess(
        response: List<CategoryOptionsModel>,
        targetItem: PropertiesDropDownUi
    ) {
        viewModelScope.launch {
            val uiModel = response.map {
                it.asPropertiesDropDownUi(
                    stringProvider.getString(R.string.other)
                )
            }
            val updatedList = updateChildItem(
                root = categoryPropertiesFlow.value,
                targetItem = targetItem,
                newChild = uiModel
            )

            hideLoading()
            _categoryPropertiesFlow.emit(updatedList)
            //notifyDataChanged.value = DataListChanged.NotifyItemChanged(changedIndex)
        }
    }

    private fun updateChildItem(
        root: List<PropertiesDropDownUi>,
        targetItem: PropertiesDropDownUi,
        newChild: List<PropertiesDropDownUi>,
    ): List<PropertiesDropDownUi> {
        val updatedList = root.mapIndexed { _, category ->
            if (category == targetItem) {
                category.copy(optionChildren = newChild)
            } else {
                if (category.optionChildren.isEmpty()) {
                    category
                } else {
                    category.copy(
                        optionChildren = updateChildItem(
                            category.optionChildren,
                            targetItem,
                            newChild
                        )
                    )
                }
            }
        }
        return updatedList
    }

    fun getSelectedDataByUser(): List<SummaryData> {
        return flattenPropertiesDropDown(categoryPropertiesFlow.value)
    }

    private fun flattenPropertiesDropDown(
        root: List<PropertiesDropDownUi>
    ): List<SummaryData> {
        val flatList = mutableListOf<SummaryData>()
        flatList.add(
            SummaryData(
                propertyName = stringProvider.getString(R.string.main_category),
                propertyValue = selectedMainCategory.value?.localName ?: "",
            )
        )
        flatList.add(
            SummaryData(
                propertyName = stringProvider.getString(R.string.sub_category),
                propertyValue = selectedSubCategory?.localName ?: "",
            )
        )
        root.forEach { item ->
            // Add the current item to the flat list
            flatList.add(
                SummaryData(propertyName = item.propertyName, propertyValue = item.selectedOption)
            )
            // Flatten and add all child options
            item.optionChildren.forEach { option ->
                flatList.add(
                    SummaryData(propertyName = item.propertyName, propertyValue = item.selectedOption)
                )
            }
        }

        return flatList
    }
}