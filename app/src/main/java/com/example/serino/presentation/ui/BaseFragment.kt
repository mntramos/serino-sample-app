package com.example.serino.presentation.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

abstract class BaseFragment : Fragment() {

    protected val mainViewModel: MainViewModel by viewModels()

    protected fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }
}