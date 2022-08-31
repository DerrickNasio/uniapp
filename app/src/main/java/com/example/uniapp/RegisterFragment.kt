package com.example.uniapp

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.uniapp.databinding.FragmentRegisterBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter.ISO_LOCAL_DATE
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    // Array of nationalities
    private val countriesArray =
        arrayOf("Kenya", "Uganda", "Tanzania", "Rwanda", "Burundi", "South Sudan")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Access the Nationalities spinner
        val spinnerCountries: Spinner = binding.spinnerCountries

        // Create an ArrayAdapter using the string array and a default spinner layout
        val aa =
            activity?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, countriesArray) }
        // Specify the layout to use when the list of choices appears
        aa!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(spinnerCountries)
        {
            // Apply the adapter to the spinner
            adapter = aa
            setSelection(0, false)
            prompt = "Select your country of citizenship"
            gravity = android.view.Gravity.CENTER
        }

        binding.buttonRegisterToConfirm.setOnClickListener {
            val student1 = obtainStudentData()
            ConfirmFragment(student1)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun obtainStudentData(): Student {

        // creating variables for values in EditTexts
        val editTextSurname: EditText = binding.editTextSurname
        val editTextOtherNames: EditText = binding.editTextOtherNames
        val editEmailAddress: EditText = binding.editEmailAddress
        val editPhoneNumber: EditText = binding.editPhoneNumber
        val editIdNumber: EditText = binding.editIdNumber

        val radioGroupGender: RadioGroup = binding.radioGroupGender
        // Getting the checked radio button id from the radio group
        val gender: String = when (radioGroupGender.checkedRadioButtonId) {
            R.id.radioMale -> "Male"
            R.id.radioFemale -> "Female"
            else -> ""
        }

        val surname = editTextSurname.text.toString()
        val otherNames = editTextOtherNames.text.toString()

        val cal = Calendar.getInstance()
        // when you click on the EditText,
        // show DatePickerDialog that is set with OnDateSetListener
        binding.editDateOfBirth.setOnClickListener {
            val datePickerDialog =
                activity?.let { it1 ->
                    DatePickerDialog(
                        it1,
                        // create an OnDateSetListener
                        { _, year, monthOfYear, dayOfMonth ->
                            (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                val dateOfBirth = LocalDate.of(year, monthOfYear, dayOfMonth)
                                dateOfBirth.format(ISO_LOCAL_DATE)
                            } else {
                                val dateOfBirth = Date(year, monthOfYear, dayOfMonth)
                                val pattern = "yyyy-MM-dd" // mention the format you need
                                val simpleDateFormat = SimpleDateFormat(pattern)
                                simpleDateFormat.format(dateOfBirth)
                            }).apply { binding.editDateOfBirth.setText(this) }
                        },
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)
                    )
                }
            datePickerDialog!!.show()
        }

        val emailAddress = editEmailAddress.text.toString()
        val phoneNumber = editPhoneNumber.text.toString()

        // Identification document input
        val radioGroupIdentity: RadioGroup = binding.radioGroupIdentity
        val identityDocument: String = when (radioGroupIdentity.checkedRadioButtonId) {
            R.id.radioID -> "National ID"
            R.id.radioBirthCertificate -> "Birth Certificate"
            else -> ""
        }
        val identityNumber = editIdNumber.text.toString()

        // Select nationality
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

        // calling method to add
        // name to our database
        return Student(
            surname,
            otherNames,
            gender,
            binding.editDateOfBirth.text.toString(),
            emailAddress,
            phoneNumber,
            identityDocument,
            identityNumber,
            spinnerCountries.selectedItem.toString()
        )
    }
}