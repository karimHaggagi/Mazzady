package com.example.mazadytask.category.presentation.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mazadytask.category.domain.model.CategoryModel
import com.example.mazadytask.category.domain.model.ChildrenModel
import com.example.mazadytask.core.presentation.BaseFragment
import com.example.mazadytask.core.presentation.resetOption
import com.example.mazadytask.core.presentation.setUpDropDown
import com.example.mazadytask.databinding.FragmentCategoryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryFragment : BaseFragment<CategoriesViewModel>() {
    private lateinit var binding: FragmentCategoryBinding
    private val categoryViewModel: CategoriesViewModel by viewModels()
    private lateinit var adapter: CategoriesOptionAdapter
    override fun getViewModel(): CategoriesViewModel {
        return categoryViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(inflater, container, false)

        initUi()

        observeData()
        return binding.root
    }

    private fun initUi() {
        adapter = CategoriesOptionAdapter { selectedPosition: Int, item: PropertiesDropDownUi ->
            categoryViewModel.setSelectedOption(selectedPosition, item)
        }
        val summaryAdapter = SummaryAdapter()
        binding.summaryLayout.rvSummary.adapter = summaryAdapter
        binding.rvCategoryProperty.adapter = adapter
        binding.btnSubmit.setOnClickListener {
            val result = categoryViewModel.getSelectedDataByUser()
            summaryAdapter.submitList(result)
            binding.summaryLayout.root.visibility = View.VISIBLE
        }
        binding.summaryLayout.btnDone.setOnClickListener {
            binding.summaryLayout.root.visibility = View.GONE
        }
    }


    private fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                launch {
                    categoryViewModel.categoriesFlow.collectLatest {
                        setCategoryData(it)
                    }
                }
                launch {
                    categoryViewModel.subCategoryFlow.collectLatest {
                        setSubCategoryData(it)
                    }
                }

                launch {
                    categoryViewModel.categoryPropertiesFlow.collectLatest {
                        setUpDropDownMenuList(it)
                    }
                }
            }
        }
    }

    private fun setUpDropDownMenuList(downUiList: List<PropertiesDropDownUi>) {
        adapter.submitList(downUiList)
    }

    private fun setCategoryData(categoryModelList: List<CategoryModel>?) {
        val categoryNames = categoryModelList?.map { it.localName }

        binding.categoryDropDown.setUpDropDown(requireContext(), categoryNames, onItemClick = {
            categoryViewModel.setSelectedCategory(it)
            binding.childrenDropDown.resetOption()
        })
    }

    private fun setSubCategoryData(childrenModel: List<ChildrenModel>?) {
        val subCategory = childrenModel?.map { it.localName }

        binding.childrenDropDown.setUpDropDown(
            requireContext(),
            subCategory,
            onItemClick = {
                categoryViewModel.setSelectedSubCategory(it)
            })
    }


}