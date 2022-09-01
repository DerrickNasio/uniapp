package com.example.uniapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.uniapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonMainToUndergraduate.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_undergraduateFragment)
        }

        binding.buttonMainToPostgraduate.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_postgraduateFragment)
        }

        binding.buttonMainToApplication.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_applicationFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}