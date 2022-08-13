package com.example.serino.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.serino.R
import com.example.serino.data.model.DomainProduct
import com.example.serino.databinding.FragmentHomeBinding
import com.example.serino.presentation.binding.SimpleDataBindingPresenter
import com.example.serino.presentation.ui.BaseFragment
import com.example.serino.presentation.ui.product.ProductListAdapter

class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var productListAdapter: ProductListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        DataBindingUtil.inflate<FragmentHomeBinding>(inflater,
            R.layout.fragment_home,
            container,
            false
        ).also {
            binding = it
        }

        initViews()
        initObservers()

        return binding.root
    }

    private fun initViews() {
        productListAdapter = ProductListAdapter(
            object : SimpleDataBindingPresenter() {
                override fun onClick(view: View, item: Any) {
                    if (item is DomainProduct) {
                        navigate(HomeFragmentDirections.actionHomeFragmentToProductFragment(item))
                    }
                }
            }
        )
        binding.rvProductList.apply {
            adapter = productListAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initObservers() {
        mainViewModel.productList.observe(viewLifecycleOwner) {
            it?.let {
                productListAdapter.submitList(it)
            }
        }
    }
}