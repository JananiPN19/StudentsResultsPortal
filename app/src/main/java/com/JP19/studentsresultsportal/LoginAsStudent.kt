package com.JP19.studentsresultsportal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class LoginAsStudent: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.student_login,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.submit1).setOnClickListener{
            val loginId = view.findViewById<EditText>(R.id.reg_no1).text.toString()
            val pwd = view.findViewById<EditText>(R.id.passwd1).text.toString()
            val regNumPattern = Regex("[0-9]{5}")
            val pwdPattern = Regex("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,12})")
            if(loginId.trim()!="" && pwd.trim()!=""
                && regNumPattern.matches(loginId) && pwdPattern.matches(pwd)) {
                findNavController().navigate(R.id.action_loginAsStudent_to_welcome)
            }
            else{
                Toast.makeText(context,"Login Failed!...Please enter valid credentials.",Toast.LENGTH_LONG).show()
            }
        }
    }
}