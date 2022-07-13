package com.example.edittextforcurrency

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import android.widget.*


class TestActivity : AppCompatActivity() {

    private var clicked:Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val range = findViewById<TextView>(R.id.range)
        val seekBar = findViewById<SeekBar>(R.id.seek)
        val button = findViewById<Button>(R.id.button)
        val edit = findViewById<EditText>(R.id.edit_text)

        seekBar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
              range.text= progress.toString()
                Toast.makeText(this@TestActivity, "", Toast.LENGTH_SHORT).show()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        button.setOnClickListener {
//           if ( getConnectivityStatusString(this)){
//               val date = Calendar.getInstance().timeInMillis
//try {
//    val sdf =  SimpleDateFormat("dd-MM-yyyy:HH-mm-sp", Locale.getDefault());
//    val currentDateAndTime = sdf.format( Date())
//    Log.d("test Activity", "onCreate:${currentDateAndTime}")
//}catch (e:IllegalArgumentException){
//    Log.d("test Activity", "onCreate:illegal exception")
//}
//
////               Toast.makeText(this, "has connection", Toast.LENGTH_SHORT).show()
//
//           }else{
////               Toast.makeText(this, " no connection", Toast.LENGTH_SHORT).show()
//
//           }
            clicked = !clicked


            if (clicked){
                showKeyBoard(edit,this)

            }else{
                hideKeyBoard(this,edit)
            }


        }

    }
    private fun getConnectivityStatusString(context: Context): Boolean {
        return (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
            getNetworkCapabilities(activeNetwork)?.run {
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        || hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                        || hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            } ?: false
        }
    }

    fun hideKeyBoard(context: Context,view: View){
        view.clearFocus()
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow( view.windowToken, 0)
    }
    fun showKeyBoard(view: View, context: Context){

        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        view.isFocusableInTouchMode = true
        imm.showSoftInput(view , InputMethod.SHOW_FORCED)
    }

    override fun onResume() {
        super.onResume()
        val edit = findViewById<EditText>(R.id.edit_text)
        showKeyBoard(edit,this)
        eval(Data)
    }

}

sealed class Shape{
    class Circle(var radius: Float): Shape()
    class Square(var length: Int): Shape()
    class Rectangle(var length: Int, var breadth: Int): Shape()
    object NotAShape : Shape()
}

object Data : Shape() {
}

fun eval(e: Shape) {
    when (e) {
        is Shape.Circle -> println("Circle area is ${3.14 * e.radius * e.radius}")
        is Shape.Square -> println("Square area is ${e.length * e.length}")
        is Shape.Rectangle -> println("Rectagle area is ${e.length * e.breadth}")
        is Data -> {}
        is Shape.NotAShape ->{}

        //else -> "else case is not require as all case is covered above"
        //  Shape.NotAShape ->Double.NaN

    }
}