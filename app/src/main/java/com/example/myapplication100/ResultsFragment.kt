package com.example.myapplication100

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.myapplication100.databinding.FragmentResultsBinding

class ResultsFragment : Fragment() {
    lateinit var binding: FragmentResultsBinding
    private val viewModel: ScoreViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentResultsBinding.inflate(inflater,container,false)
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.finalResult.observe(viewLifecycleOwner, Observer {
            binding.showResult.text =it
        })
    }
}