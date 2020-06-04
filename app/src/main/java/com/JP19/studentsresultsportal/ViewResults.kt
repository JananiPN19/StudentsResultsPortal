package com.JP19.studentsresultsportal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class ViewResults: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.results_view,container,false)
    }

    private val args: ViewResultsArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.back_button1).setOnClickListener {
            findNavController().navigate(R.id.action_viewResults_to_welcome)
        }

        val regNum = args.myArg
        val dbHandler = DatabaseHandler(activity)
        val st = dbHandler.ViewMarks(regNum)
        view.findViewById<TextView>(R.id.reg_num_res).text = st.regNum.toString()
        view.findViewById<TextView>(R.id.dbms_res).text = st.dbmsMark.toString()
        view.findViewById<TextView>(R.id.python_res).text = st.pyMark.toString()
        view.findViewById<TextView>(R.id.ds_res).text = st.dsMark.toString()
    }
}