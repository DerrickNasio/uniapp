package com.example.uniapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.uniapp.databinding.FragmentUndergraduateBinding
import java.io.IOException
import java.io.InputStream

class UndergraduateFragment : Fragment() {

    private var _binding: FragmentUndergraduateBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUndergraduateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var content: String? = null
        try {
            val inputStream: InputStream = requireActivity().assets.open("undergraduate.txt")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            content = String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }

        val output = binding.textviewUndergraduate
        output.text = content

        // An onClickListener for the button
        binding.buttonUndergraduateCourses.setOnClickListener {
            findNavController().navigate(R.id.action_undergraduateFragment_view_courses)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}