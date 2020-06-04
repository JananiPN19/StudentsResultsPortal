package com.JP19.studentsresultsportal

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context?): SQLiteOpenHelper(context, DATABASE_NAME,null,DATABASE_VERSION) {
    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "StudentDatabase"
        private const val TABLE_STUDENTS = "StudentTable"
        private const val KEY_REG_NUM = "reg_num"
        private const val KEY_DBMS = "dbms"
        private const val KEY_PY = "python"
        private const val KEY_DS = "ds"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createStudentsTable = (
                "CREATE TABLE $TABLE_STUDENTS "
                +"($KEY_REG_NUM INTEGER PRIMARY KEY, $KEY_DBMS INTEGER, $KEY_PY INTEGER, $KEY_DS INTEGER);")
        db?.execSQL(createStudentsTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_STUDENTS")
        onCreate(db)
    }

    fun addMarks(st: StudModelClass): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_REG_NUM,st.regNum)
        contentValues.put(KEY_DBMS,st.dbmsMark)
        contentValues.put(KEY_PY,st.pyMark)
        contentValues.put(KEY_DS,st.dsMark)

        val insertSuccess = db.insert(TABLE_STUDENTS,null,contentValues)
        db.close()
        return insertSuccess
    }
    fun updateMarks(st: StudModelClass):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_REG_NUM,st.regNum)
        contentValues.put(KEY_DBMS,st.dbmsMark)
        contentValues.put(KEY_PY,st.pyMark)
        contentValues.put(KEY_DS,st.dsMark)
        val updateSuccess = db.update(TABLE_STUDENTS,contentValues,"reg_num="+st.regNum,null)
        db.close()
        return updateSuccess
    }
    fun ViewMarks(regNum: Int): StudModelClass{
        val query = "SELECT * FROM $TABLE_STUDENTS WHERE reg_num=$regNum"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(query,null)
        } catch (e: SQLiteException){
            db.execSQL(query)
        }
        val regNumber:Int
        val dbmsRes:Int
        val pyRes:Int
        val dsRes:Int
        var stRes = StudModelClass(0,0,0,0)
        if (cursor?.moveToFirst()!!) {
            regNumber = cursor.getInt(cursor.getColumnIndex("reg_num"))
            dbmsRes = cursor.getInt(cursor.getColumnIndex("dbms"))
            pyRes = cursor.getInt(cursor.getColumnIndex("python"))
            dsRes = cursor.getInt(cursor.getColumnIndex("ds"))
            stRes = StudModelClass(regNum = regNumber, dbmsMark = dbmsRes, pyMark = pyRes, dsMark = dsRes)
        }
        return stRes
    }
}