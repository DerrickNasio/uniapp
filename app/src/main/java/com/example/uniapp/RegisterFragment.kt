package com.example.uniapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.uniapp.databinding.FragmentRegisterBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue


/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        // display selected course
        val bundle = intent.extras
        binding.editAppliedCourse.setText(bundle?.getString("courseName"))
        binding.editFaculty.setText(bundle?.getString("faculty"))

        // Create an ArrayAdapter for the nationality spinner using the string array and a default spinner layout
        val spinnerCountries: Spinner = binding.spinnerCountries
        val countriesArray =
            arrayOf("Kenya", "Uganda", "Tanzania", "Rwanda", "Burundi", "South Sudan")
        val appContext = context?.applicationContext!!
        val aa = ArrayAdapter(appContext, android.R.layout.simple_spinner_item, countriesArray)
        // Specify the layout to use when the list of choices appears
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        with(spinnerCountries)
        {
            // Apply the adapter to the spinner
            adapter = aa
            setSelection(0, false)
            prompt = "Select your country of citizenship"
            gravity = android.view.Gravity.CENTER
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // creating variables for values in EditTexts
        val editTextSurname: EditText = binding.editTextSurname
        val editTextOtherNames: EditText = binding.editTextOtherNames
        val editDateOfBirth: EditText = binding.editDateOfBirth
        val editEmailAddress: EditText = binding.editEmailAddress
        val editPhoneNumber: EditText = binding.editPhoneNumber
        val editIdNumber: EditText = binding.editIdNumber

        // obtaining EditText inputs
        val textSurname = editTextSurname.text.toString()
        val textOtherNames = editTextOtherNames.text.toString()
        val textDateOfBirth = editDateOfBirth.text.toString()
        val textEmailAddress = editEmailAddress.text.toString()
        val textPhoneNumber = editPhoneNumber.text.toString()
        val textIdentityNumber = editIdNumber.text.toString()

        // set onItemSelectedListener to nationality spinner
        val spinnerCountries: Spinner = binding.spinnerCountries
        spinnerCountries.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                // An item was selected. Retrieve the selected item.
                parent.getItemAtPosition(position) as String
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // TODO("Not yet implemented")
            }
        }

        // obtaining input from Gender radio group
        val radioGroupGender: RadioGroup = binding.radioGroupGender
        val textGender: String = when (radioGroupGender.checkedRadioButtonId) {
            R.id.radioMale -> "Male"
            R.id.radioFemale -> "Female"
            else -> ""
        }

        binding.buttonRegisterToConfirm.setOnClickListener {

            // Write a message to the database
            val databaseReference = FirebaseDatabase.getInstance().getReference("students")

            val student = Student(
                textSurname,
                textOtherNames,
                textGender,
                textDateOfBirth,
                textEmailAddress,
                textPhoneNumber,
                textIdentityNumber,
                spinnerCountries.selectedItem.toString()
            )
            databaseReference.child("emailAddress").child(textEmailAddress).setValue(student)

            findNavController().navigate(R.id.action_registerFragment_to_dashboardFragment)
        }

        binding.buttonPick.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "file/*"
            startActivityForResult(intent, 1)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}