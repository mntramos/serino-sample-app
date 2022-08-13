package com.example.serino.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.serino.data.database.ProductDatabase

abstract class BaseFragment : Fragment() {

    protected val mainViewModel: MainViewModel by activityViewModels {
        MainViewModelFactory(
            ProductDatabase.getInstance(requireContext()).productDatabaseDao
        )
    }

    protected fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }
}