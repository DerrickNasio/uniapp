package com.example.uniapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.uniapp.databinding.FragmentPostgraduateBinding
import java.io.IOException
import java.io.InputStream

class PostgraduateFragment : Fragment() {

    private var _binding: FragmentPostgraduateBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPostgraduateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var content: String? = null
        try {
            val inputStream: InputStream = requireActivity().assets.open("postgraduate.txt")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            content = String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }

        val output = binding.textviewPostgraduate
        output.text = content

        // An onClickListener for the button
        binding.buttonPostgraduateCourses.setOnClickListener {
            findNavController().navigate(R.id.action_postgraduateFragment_view_courses)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}