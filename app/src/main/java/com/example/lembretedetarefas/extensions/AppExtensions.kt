package com.example.lembretedetarefas.extensions

import com.google.android.material.textfield.TextInputLayout
import java.util.*
import java.text.SimpleDateFormat as SimpleDateFormat1

private val locale = locale("pt","BR")

fun Date.format(): String{
    return SimpleDateFormat1("dd/mm/yyyy", locale).format(this)



}
var TextInputLayout.text:String
get()=editText?.text?.toString() ?: ""
set(value){
    editText?.setText(value)
}