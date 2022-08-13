package com.example.serino.presentation.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.serino.R
import com.example.serino.databinding.FragmentProductBinding
import com.example.serino.presentation.ui.BaseFragment

class ProductFragment : BaseFragment() {

    private lateinit var binding: FragmentProductBinding

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
            item = args.item
        }.also {
            binding = it
        }

        return binding.root
    }
}