package com.example.uniapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.uniapp.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*val dashboardBinding = DataBindingUtil.setContentView<ActivityDashboardBinding>(this,R.layout.activity_dashboard)
        val loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        dashboardBinding.setLoginViewModel(loginViewModel)*/

        binding.buttonDashboardToEdit.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_registerFragment)
        }

        binding.buttonDashboardToCourse.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_programmesFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}