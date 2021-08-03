package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Button
import com.example.calculatorapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var isNumberInInput = false
    var isOperationInInput = false
    var isDotInInput = false
    var isEqualInInput = false
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        backspace.setOnClickListener {
            binding.input.text = binding.input.text.dropLast(1)
            isOperationInInput = false
            isDotInInput = false
            isNumberInInput = true
            isEqualInInput = false
        }

    }

    // Numbers Function
    fun numbersButton(view: View) {
        val selectedButton = view as Button
        if (!isEqualInInput) {
            binding.input.append(selectedButton.text)
            isNumberInInput = true
        }
    }

    // Clear Button Function
    fun clearButton(view: View) {
        binding.input.text = ""
        binding.output.text = ""
        isNumberInInput = false
        isOperationInInput = false
        isDotInInput = false
        isEqualInInput = false
        binding.input.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36f)
        binding.output.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50f)

    }

    // Operation Function
    fun operationButton(view: View) {
        val selectedButton = view as Button
        if (isNumberInInput && !isOperationInInput) {
            binding.input.append(selectedButton.text)
            isOperationInInput = true
            isNumberInInput = false
            isDotInInput = false
        }
        if (isEqualInInput) {
            binding.input.text = binding.output.text
            binding.input.append(selectedButton.text)
            binding.output.text = ""
            binding.input.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36f)
            binding.output.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50f)
            isNumberInInput = false
            isOperationInInput = true
            isEqualInInput = false
            isDotInInput = false
        }
    }

    // Dot Function
    fun dotButton(view: View) {
        val selectedButton = view as Button
        if (isNumberInInput && !isDotInInput) {
            binding.input.append(selectedButton.text)
            isNumberInInput = false
            isDotInInput = true
        } else if (!isNumberInInput && !isDotInInput) {
            binding.input.append("0.")
            isNumberInInput = false
            isDotInInput = true
        }
    }

    //Equal Function
    fun equalButton(view: View) {
        textSize()
        var prefix = ""
        var inputValue = binding.input.text.toString()
        if (isNumberInInput && isOperationInInput && !isEqualInInput) {
            binding.input.append("=")
            isEqualInInput = true
            isDotInInput = false
            isNumberInInput = false
            if (inputValue.startsWith('-')) {
                prefix = "-"
                inputValue = inputValue.substring(1)
            }
            if (inputValue.contains('+')) {
                val spiltNumbers = inputValue.split('+')
                var firstNumber = spiltNumbers[0]
                var secondNumber = spiltNumbers[1]
                if (!prefix.isEmpty()) {
                    firstNumber = prefix + firstNumber
                }
                val result = firstNumber.toDouble() + secondNumber.toDouble()
                binding.output.text = result.toString()
            }
            if (inputValue.contains('×')) {
                val spiltNumber = inputValue.split('×')
                var firstNumber = spiltNumber[0]
                var secondNumber = spiltNumber[1]
                if (!prefix.isEmpty()) {
                    firstNumber = prefix + firstNumber
                }
                val result = firstNumber.toDouble() * secondNumber.toDouble()
                binding.output.text = result.toString()
            }
            if (inputValue.contains('÷')) {
                val spiltNumber = inputValue.split('÷')
                var firstNumber = spiltNumber[0]
                var secondNumber = spiltNumber[1]
                if (!prefix.isEmpty()) {
                    firstNumber = prefix + firstNumber
                }
                val result = firstNumber.toDouble() / secondNumber.toDouble()
                binding.output.text = result.toString()
            }
            if (inputValue.contains('-')) {
                val spiltNumber = inputValue.split('-')
                var firstNumber = spiltNumber[0]
                var secondNumber = spiltNumber[1]
                if (!prefix.isEmpty()) {
                    firstNumber = prefix + firstNumber
                }
                val result = firstNumber.toDouble() - secondNumber.toDouble()
                binding.output.text = result.toString()
            }
            if (inputValue.contains('%')) {
                val spiltNumbers = inputValue.split("%")
                var firstNum = spiltNumbers[0]
                var secondNum = spiltNumbers[1]
                if (!prefix.isEmpty()) {
                    firstNum = prefix + firstNum
                }
                val result = firstNum.toDouble() * secondNum.toDouble() / 100
                binding.output.text = result.toString()
            }
        }
    }

    // change text size
    fun textSize() {
        if (isEqualInInput) {
            binding.input.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36f)
            binding.output.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50f)
        }
    }


}
