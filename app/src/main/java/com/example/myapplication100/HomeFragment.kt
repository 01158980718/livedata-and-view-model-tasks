package com.example.myapplication100

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.myapplication100.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: ScoreViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.scoreTeamOne.observe(viewLifecycleOwner, Observer {
            binding.score1.text = it.toString()
        })
        viewModel.scoreTeamTwo.observe(viewLifecycleOwner, Observer {
            binding.score2.text = it.toString()
        })

        binding.increase1.setOnClickListener {
            viewModel.increaseTeamOneScore()
        }
        binding.increase2.setOnClickListener {
            viewModel.increaseTeamTwoScore()
        }
        binding.show.setOnClickListener{
            viewModel.checkFinalResult()
            findNavController().navigate(R.id.resultsFragment)
        }
    }
}
