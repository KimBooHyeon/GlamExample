package com.cupist.glam.custom

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import com.cupist.glam.R
import com.cupist.glam.databinding.DialogEditBinding

class CustomEditDialog(context: Context): Dialog(context, R.style.CustomAlertDialog) {
    val binding: DialogEditBinding by lazy { DialogEditBinding.inflate(LayoutInflater.from(context)) }

    private lateinit var listener: DialogEditChangeListener

    init {
        setContentView(binding.root)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.etIntroduction.requestFocus()
        binding.ivBack.setOnClickListener {
            dismiss()
        }
        binding.tvDone.setOnClickListener {
            listener.onChanged(binding.etIntroduction.text.toString())
            dismiss()
        }
    }

    fun setDefaultText(text: String) {
        binding.etIntroduction.setText(text)
    }

    fun setOnItemChanged(listener: (String) -> Unit) {
        this.listener = object: DialogEditChangeListener {
            override fun onChanged(text: String) {
                listener(text)
            }
        }
    }

    interface DialogEditChangeListener {
        fun onChanged(text: String)
    }
}