package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.calculatorapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var isNumberInInput = true
    var isOperationInInput = false
    var isDotInInput = false
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    // Numbers Function
    fun numbersButton(view: View) {
        val selectedButton = view as Button
        binding.input.append(selectedButton.text)
        isNumberInInput = true
    }

    // Clear Button Function
    fun clearButton(view: View) {
        binding.input.text = ""
        binding.output.text = ""
        var isNumberInInput = false
        var isOperationInInput = false
        var isDotInInput = false
    }

    // Operation Function
    fun operationButton(view: View) {
        val selectedButton = view as Button
        if (isNumberInInput && !isOperationInInput) {
            binding.input.append(selectedButton.text)
            isNumberInInput = false
            isOperationInInput = false
            isDotInInput = false
        }
    }

    // Dot Function
    fun dotButton(view: View) {
        val selectedButton = view as Button
        if (isNumberInInput && !isOperationInInput && !isDotInInput) {
            binding.input.append(selectedButton.text)

        } else if (binding.input.text.isEmpty()) {
            binding.input.append("0.")
        }
        isNumberInInput = false
        isDotInInput = true
    }
}