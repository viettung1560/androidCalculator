package com.example.calculator

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var textCalc: TextView
    lateinit var textResult: TextView

    var state: Int = 1
    var op: Int = 0 //1:+ 2:- 3:x 4:/
    var op1: Int = 0
    var op2: Int = 0
    var negative: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textCalc = findViewById(R.id.calcTextView)
        textResult = findViewById(R.id.numTextView)

        findViewById<Button>(R.id.btn_0).setOnClickListener(this)
        findViewById<Button>(R.id.btn_1).setOnClickListener(this)
        findViewById<Button>(R.id.btn_2).setOnClickListener(this)
        findViewById<Button>(R.id.btn_3).setOnClickListener(this)
        findViewById<Button>(R.id.btn_4).setOnClickListener(this)
        findViewById<Button>(R.id.btn_5).setOnClickListener(this)
        findViewById<Button>(R.id.btn_6).setOnClickListener(this)
        findViewById<Button>(R.id.btn_7).setOnClickListener(this)
        findViewById<Button>(R.id.btn_8).setOnClickListener(this)
        findViewById<Button>(R.id.btn_9).setOnClickListener(this)
        findViewById<Button>(R.id.btn_plus).setOnClickListener(this)
        findViewById<Button>(R.id.btn_minus).setOnClickListener(this)
        findViewById<Button>(R.id.btn_time).setOnClickListener(this)
        findViewById<Button>(R.id.btn_divide).setOnClickListener(this)
        findViewById<Button>(R.id.btn_result).setOnClickListener(this)
        findViewById<Button>(R.id.btn_clear_entry).setOnClickListener(this)
        findViewById<Button>(R.id.btn_clear).setOnClickListener(this)
        findViewById<Button>(R.id.btn_backspace).setOnClickListener(this)
        findViewById<Button>(R.id.btn_flip).setOnClickListener(this)
    }
    override fun onClick(p0: View?) {
        val id = p0?.id
        if (id == R.id.btn_0) {
            addDigit(0)
        } else if (id == R.id.btn_1) {
            addDigit(1)
        } else if (id == R.id.btn_2) {
            addDigit(2)
        } else if (id == R.id.btn_3) {
            addDigit(3)
        } else if (id == R.id.btn_4) {
            addDigit(4)
        } else if (id == R.id.btn_5) {
            addDigit(5)
        } else if (id == R.id.btn_6) {
            addDigit(6)
        } else if (id == R.id.btn_7) {
            addDigit(7)
        } else if (id == R.id.btn_8) {
            addDigit(8)
        } else if (id == R.id.btn_9) {
            addDigit(9)
        } else if (id == R.id.btn_plus) {
            if (state == 3)
                op1 = textResult.text.toString().toInt()
            if (op1 != 0) {
                textCalc.text = "$op1 +"

                op = 1
                state = 2
                negative = false
            }
        } else if (id == R.id.btn_minus) {
            if (state == 3)
                op1 = textResult.text.toString().toInt()
            if (op1 != 0) {
                textCalc.text = "$op1 -"

                op = 2
                state = 2
                negative = false
            }
        } else if (id == R.id.btn_time) {
            if (state == 3)
                op1 = textResult.text.toString().toInt()
            if (op1 != 0) {
                textCalc.text = "$op1 x"

                op = 3
                state = 2
                negative = false
            }
        } else if (id == R.id.btn_divide) {
            if (state == 3)
                op1 = textResult.text.toString().toInt()
            if (op1 != 0) {
                textCalc.text = "$op1 /"

                op = 4
                state = 2
                negative = false
            }
        } else if (id == R.id.btn_result) {
            if (state == 2 && op2 != 0) {
                var result = 0
                if (op == 1) {
                    result = op1 + op2
                    textCalc.text = "$op1 + $op2 ="
                } else if (op == 2) {
                    result = op1 - op2
                    textCalc.text = "$op1 - $op2 ="
                } else if (op == 3) {
                    result = op1 * op2
                    textCalc.text = "$op1 x $op2 ="
                } else if (op == 4) {
                    result = op1 / op2
                    textCalc.text = "$op1 / $op2 ="
                }
                textResult.text = "$result"
                state = 3
                op1 = 0
                op2 = 0
                op = 0
                negative = false
            }
        } else if (id == R.id.btn_clear_entry){
            if (state == 1){
                op1 = 0
                negative = false
                textResult.text = "0"
            }
            else if (state == 2){
                op2 = 0
                negative = false
                textResult.text = "0"
            }
        } else if (id == R.id.btn_clear){
            state = 1
            op1 = 0
            op2 = 0
            op = 0
            negative = false
            textResult.text = "0"
            textCalc.text = ""
        } else if (id == R.id.btn_backspace){
            if (state == 1){
                if (op1 != 0) {
                    op1 /= 10
                    textResult.text = "$op1"
                }
            }
            else if (state == 2){
                if (op2 != 0) {
                    op2 /= 10
                    textResult.text = "$op2"
                }
            }
        } else if (id == R.id.btn_flip){
            negative = !negative
            if (state == 1) {
                op1 -= 2 * op1
                textResult.text = "$op1"
            }
            else if (state == 2) {
                op2 -= 2 * op2
                textResult.text = "$op2"
            }

        }
    }

    fun addDigit(c: Int) {
        if (state == 1) {
            if (!negative) {
                op1 = op1 * 10 + c
            }
            else{
                op1 = op1 * 10 - c
            }
            textResult.text = "$op1"
        } else if (state == 2) {
            if (!negative) {
                op2 = op2 * 10 + c
            }
            else{
                op2 = op2 * 10 - c
            }
            textResult.text = "$op2"
        }
    }
}