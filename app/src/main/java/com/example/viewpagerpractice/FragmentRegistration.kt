package com.example.viewpagerpractice

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
class FragmentRegistration : Fragment() {

    private lateinit var dbHelper: DatabaseHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registration, container, false)

        dbHelper = DatabaseHelper(requireContext())

        val loginEditText = view.findViewById<EditText>(R.id.loginEditText)
        val passwordEditText = view.findViewById<EditText>(R.id.passwordEditText)
        val registerButton = view.findViewById<Button>(R.id.registerButton)
        val loginButton = view.findViewById<Button>(R.id.loginButton)

        registerButton.setOnClickListener {
            val login = loginEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (login.isNotEmpty() && password.isNotEmpty()) {
                val db = dbHelper.writableDatabase
                val contentValues = ContentValues().apply {
                    put(DatabaseHelper.COLUMN_USERNAME, login)
                    put(DatabaseHelper.COLUMN_PASSWORD, password)
                }
                db.insert(DatabaseHelper.TABLE_NAME, null, contentValues)
                Toast.makeText(requireContext(),
                    getString(R.string.registr_succes), Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_registration_to_login)
            } else {
                Toast.makeText(requireContext(),
                    getString(R.string.write_login_password), Toast.LENGTH_SHORT).show()
            }
        }

        loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_registration_to_login)
        }

        return view
    }
}



