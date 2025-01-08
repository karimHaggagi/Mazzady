package com.example.mazadytask.category.presentation.categories

import com.example.mazadytask.category.domain.model.CategoryOptionsModel


/**
 * created by Karim Haggagi on 1/5/25
 **/

data class PropertiesDropDownUi(
    val propertyName: String = "",
    val propertyId: Int = 0,
    var inputType: InputType = InputType.Default,
    var customInput: String = "",
    val options: List<CategoryOptions> = emptyList(),
    val optionChildren: List<PropertiesDropDownUi> = mutableListOf()
) {
    val selectedOption: String
        get() = when (inputType) {
            InputType.Default -> ""
            InputType.FromUser -> {
                customInput
            }

            is InputType.SelectedOption -> {
                options[(inputType as InputType.SelectedOption).index].optionName
            }
        }

}

data class CategoryOptions(
    val optionName: String = "",
    val optionId: Int = 0,
    val hasChild: Boolean = false
)

data class SummaryData(
    val propertyName:String,
    val propertyValue:String
)
fun CategoryOptionsModel.asPropertiesDropDownUi(option: String?): PropertiesDropDownUi {
    val mutableOptions =
        options.map {
            CategoryOptions(
                optionName = it.localName,
                optionId = it.id,
                hasChild = it.hasChild
            )
        }.toMutableList()

    if (option != null) {
        mutableOptions.add(0, CategoryOptions(option, 0))
    }

    return PropertiesDropDownUi(
        propertyName = localName,
        propertyId = id,
        options = mutableOptions
    )
}

sealed interface InputType {
    data object Default : InputType
    data object FromUser : InputType
    data class SelectedOption(val index: Int) : InputType
}

