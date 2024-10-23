package com.example.viewpagerpractice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController


class FragmentLogin : Fragment() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        dbHelper = DatabaseHelper(requireContext())

        val loginEditText = view.findViewById<EditText>(R.id.loginEditText)
        val passwordEditText = view.findViewById<EditText>(R.id.passwordEditText)
        val loginButton = view.findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val login = loginEditText.text.toString()
            val password = passwordEditText.text.toString()

            val db = dbHelper.readableDatabase
            val cursor = db.query(
                DatabaseHelper.TABLE_NAME,
                arrayOf(DatabaseHelper.COLUMN_PASSWORD),
                "${DatabaseHelper.COLUMN_USERNAME} = ?",
                arrayOf(login),
                null,
                null,
                null
            )

            if (cursor.moveToFirst()) {
                val storedPassword = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PASSWORD))
                if (storedPassword == password) {
                    findNavController().navigate(R.id.action_login_to_main)
                } else {
                    Toast.makeText(requireContext(),
                        getString(R.string.incorrect_text), Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), R.string.incorrect_text, Toast.LENGTH_SHORT).show()
            }
            cursor.close()
        }

        return view
    }
}


