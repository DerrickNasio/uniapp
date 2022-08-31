package com.example.uniapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.uniapp.databinding.FragmentApplicationBinding
import java.io.IOException
import java.io.InputStream

class ApplicationFragment : Fragment() {
    private var _binding: FragmentApplicationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentApplicationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var content: String? = null
        try {
            val inputStream: InputStream = requireActivity().assets.open("application.txt")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            content = String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }

        val output = binding.textviewApplication
        output.text = content
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}