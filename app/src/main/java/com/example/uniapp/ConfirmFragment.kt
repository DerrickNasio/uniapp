package com.example.uniapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.uniapp.databinding.ContentScrollingBinding
import com.example.uniapp.databinding.FragmentConfirmBinding

/**
 * A simple [Fragment] subclass.
 */
class ConfirmFragment(student: Student) : Fragment() {
    private var _student = student

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentConfirmBinding =
            FragmentConfirmBinding.inflate(inflater, container, false)
        val contentScrollingBinding = ContentScrollingBinding.bind(fragmentConfirmBinding.root)

        // Display student details in ContentScrolling
        with(contentScrollingBinding) {
            viewTextSurname.text = _student.surname
            viewTextOtherNames.text = _student.otherNames
            viewTextGender.text = _student.gender
            viewDateOfBirth.text = _student.dateOfBirth
            viewEmailAddress.text = _student.emailAddress
            viewPhoneNumber.text = _student.phoneNumber
            viewIdNumber.text = _student.identityNumber
            viewTextNationality.text = _student.country
        }

        fragmentConfirmBinding.buttonEdit.setOnClickListener {
            findNavController().navigate(R.id.action_confirmFragment_to_registerFragment)
        }

        fragmentConfirmBinding.buttonSubmit.setOnClickListener {
            /*val db = activity?.let { it1 -> SQLiteDatabaseHelper(it1, null) }

            db!!.writeItemToDatabase(_student)*/
            findNavController().navigate(R.id.action_confirmFragment_to_dashboardFragment)
        }

        return fragmentConfirmBinding.root
    }
}
