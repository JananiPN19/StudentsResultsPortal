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

class Welcome: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.welcome_page,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.submit3).setOnClickListener{
            val textStr = view.findViewById<EditText>(R.id.reg_num_enter)
            val rNum = textStr.text.toString().toInt()
            val pattern = Regex("[0-9]{5}")
            if(textStr.text.toString().trim()!="" && pattern.matches(textStr.text.toString())) {
                val action = WelcomeDirections.actionWelcomeToViewResults(rNum)
                findNavController().navigate(action)
            }
            else{
                Toast.makeText(context,"Please Enter the Register number.",Toast.LENGTH_LONG).show()
            }
        }
    }
}