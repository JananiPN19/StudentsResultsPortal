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
import kotlinx.android.synthetic.main.update_marks.*

class MarksUpdate: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.update_marks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.insert_button).setOnClickListener {
            val dbHandler = DatabaseHandler(activity)
            val regNo = view.findViewById<EditText>(R.id.reg_num_enter).text.toString()
            val dbms = view.findViewById<EditText>(R.id.dbms_edit).text.toString()
            val py = view.findViewById<EditText>(R.id.py_edit).text.toString()
            val ds = view.findViewById<EditText>(R.id.ds_edit).text.toString()
            val regNumPattern = Regex("[0-9]{5}")
            val markPattern = Regex("1?[0-9]{2}")
            if (regNo.trim() != "" && dbms.trim() != "" && py.trim() != "" && ds.trim() != ""
                && regNumPattern.matches(regNo) && markPattern.matches(dbms) && markPattern.matches(py) && markPattern.matches(ds)) {
                val status = dbHandler.addMarks(
                    StudModelClass(
                        Integer.parseInt(regNo),
                        Integer.parseInt(dbms),
                        Integer.parseInt(py),
                        Integer.parseInt(ds)
                    )
                )
                if (status > -1) {
                    Toast.makeText(context, "Insert Successful.", Toast.LENGTH_LONG).show()
                    reg_num_enter.text.clear()
                    dbms_edit.text.clear()
                    py_edit.text.clear()
                    ds_edit.text.clear()
                }
            } else {
                Toast.makeText(
                    context,
                    "Insertion failed! Please make sure you have entered proper values",
                    Toast.LENGTH_LONG).show()
            }
        }
        view.findViewById<Button>(R.id.update_button).setOnClickListener {
            val dbHandler = DatabaseHandler(activity)
            val regNumPattern = Regex("[0-9]{5}")
            val markPattern = Regex("1?[0-9]{2}")
            val regNo = view.findViewById<EditText>(R.id.reg_num_enter).text.toString()
            val dbms = view.findViewById<EditText>(R.id.dbms_edit).text.toString()
            val py = view.findViewById<EditText>(R.id.py_edit).text.toString()
            val ds = view.findViewById<EditText>(R.id.ds_edit).text.toString()
            if (regNo.trim() != "" && dbms.trim() != "" && py.trim() != "" && ds.trim() != ""
                && regNumPattern.matches(regNo) && markPattern.matches(dbms) && markPattern.matches(py) && markPattern.matches(ds)) {
                val status = dbHandler.updateMarks(
                    StudModelClass(
                        Integer.parseInt(regNo),
                        Integer.parseInt(dbms),
                        Integer.parseInt(py),
                        Integer.parseInt(ds)
                    )
                )
                if (status > -1) {
                    Toast.makeText(context, "Update Successful.", Toast.LENGTH_LONG).show()
                    reg_num_enter.text.clear()
                    dbms_edit.text.clear()
                    py_edit.text.clear()
                    ds_edit.text.clear()
                }
            } else {
                Toast.makeText(
                    context,
                    "Update Failed. Please make sure you have entered all the values.",
                    Toast.LENGTH_LONG
                ).show()

            }
        }

        view.findViewById<Button>(R.id.back_button1).setOnClickListener{
            findNavController().navigate(R.id.action_marksUpdate_to_index)
        }
    }
}