package com.example.serino.presentation.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.serino.R
import com.example.serino.data.model.NetworkProduct
import com.example.serino.data.model.Product
import com.example.serino.databinding.FragmentHomeBinding
import com.example.serino.presentation.binding.SimpleDataBindingPresenter
import com.example.serino.presentation.ui.BaseFragment
import com.example.serino.presentation.ui.common.item_decoration.VerticalSpaceItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeListAdapter: HomeListAdapter
    private val homeListAdapter2 = HomeListAdapter2()

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
//        getProducts()

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
//            adapter = homeListAdapter2
            layoutManager = LinearLayoutManager(context)

            val spacing = resources.getDimensionPixelSize(R.dimen.spacing_8dp)
            addItemDecoration(VerticalSpaceItemDecoration(spacing, spacing))
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

    private var job: Job? = null
    private fun getProducts() {
        Log.d("HomeFragment", "getProducts")
        job?.cancel()
        job = lifecycleScope.launch {
            mainViewModel.getProducts2().collectLatest {
                Log.d("HomeFragment", "List $it")
                homeListAdapter2.submitData(it)
            }
        }
    }
}