package com.example.serino.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.serino.R
import com.example.serino.data.model.Product
import com.example.serino.databinding.FragmentHomeBinding
import com.example.serino.presentation.binding.SimpleDataBindingPresenter
import com.example.serino.presentation.ui.BaseFragment
import com.example.serino.presentation.ui.common.item_decoration.VerticalSpaceItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeListAdapter: HomeListAdapter

    private var errorToast: Toast? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        DataBindingUtil.inflate<FragmentHomeBinding>(inflater,
            R.layout.fragment_home,
            container,
            false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = mainViewModel
        }.also {
            binding = it
        }

        setTitle("Products")
        initViews()
        initObservers()

        return binding.root
    }

    private fun initViews() {
        homeListAdapter = HomeListAdapter(
            object : SimpleDataBindingPresenter() {
                override fun onClick(view: View, item: Any) {
                    if (item is Product) {
                        navigate(HomeFragmentDirections.actionHomeFragmentToProductFragment(item))
                    }
                }
            }
        )
        binding.rvProductList.apply {
            adapter = homeListAdapter
            layoutManager = LinearLayoutManager(context)

            val spacing = resources.getDimensionPixelSize(R.dimen.spacing_8dp)
            addItemDecoration(VerticalSpaceItemDecoration(spacing, spacing))

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    if (!recyclerView.canScrollVertically(1)) {
                        recyclerView.adapter?.itemCount?.let { mainViewModel.getProducts(it + 10) }
                    }
                }
            })
        }
    }

    private fun initObservers() {
        mainViewModel.productList.observe(viewLifecycleOwner) {
            it?.let {
                homeListAdapter.submitList(it)
            }
        }
        mainViewModel.isError.observe(viewLifecycleOwner) {
            it?.let {
                errorToast?.cancel()
                if (it.isNotBlank()) {
                    errorToast = Toast.makeText(context, it, Toast.LENGTH_SHORT).apply { show() }
                }
            }
        }
    }
}