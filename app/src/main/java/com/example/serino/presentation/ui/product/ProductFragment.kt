package com.example.serino.presentation.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.serino.R
import com.example.serino.databinding.FragmentProductBinding
import com.example.serino.presentation.ui.BaseFragment
import com.example.serino.presentation.ui.common.item_decoration.HorizontalSpaceItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFragment : BaseFragment() {

    private lateinit var binding: FragmentProductBinding
    private val productImageListAdapter = ProductImageListAdapter()

    private val args: ProductFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        DataBindingUtil.inflate<FragmentProductBinding>(inflater,
            R.layout.fragment_product,
            container,
            false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
            item = args.item.also { mainViewModel.setCurrentProduct(it) }
        }.also {
            binding = it
        }

        setTitle(args.item.title)
        initViews()
        initObservers()

        return binding.root
    }

    private fun initViews() {
        binding.rvProductImageList.apply {
            adapter = productImageListAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            val spacing = resources.getDimensionPixelSize(R.dimen.spacing_8dp)
            addItemDecoration(HorizontalSpaceItemDecoration(spacing, spacing))
        }
    }

    private fun initObservers() {
        mainViewModel.currentProduct.observe(viewLifecycleOwner) {
            it?.let {
                productImageListAdapter.data = it.images
            }
        }
    }
}