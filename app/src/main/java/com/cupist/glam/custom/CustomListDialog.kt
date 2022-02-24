package com.cupist.glam.custom

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import com.cupist.glam.Global
import com.cupist.glam.databinding.DialogListBinding
import com.cupist.glam.network.model.Meta
import com.cupist.glam.utils.Constants

class CustomListDialog(context: Context) : Dialog(context) {
    val binding: DialogListBinding by lazy { DialogListBinding.inflate(LayoutInflater.from(context)) }
    var type = MutableLiveData<Int>()
    private val items: ArrayList<Meta.KeyNamePair> = ArrayList()

    private lateinit var listener : DialogOnItemSelectedListener

    init {
        setContentView(binding.root)
    }

    fun setType(id: Int) {
        type.value = id
        setDialogTitle()
    }

    private fun setDialogTitle() {
        when(type.value) {
            Constants.DIALOG_TYPE_HEIGHT -> binding.tvTitle.text = "키"
            Constants.DIALOG_TYPE_BODY_TYPE -> binding.tvTitle.text = "체형"
            Constants.DIALOG_TYPE_JOB -> binding.tvTitle.text = "직업"
            Constants.DIALOG_TYPE_EDUCATION -> binding.tvTitle.text = "학력"
        }
    }

    fun setList(list: ArrayList<Meta.KeyNamePair>) {
        items.clear()
        items.addAll(list)

        binding.list.removeAllViews()
        items.forEach { item ->
            val textView = TextView(context)
            textView.text = item.name
            textView.setOnClickListener {
                listener.onItemSelected(item.key)
                dismiss()
            }
            binding.list.addView(textView)
        }
        if (items.size > 7) {
            binding.scroll.layoutParams.height = Global.INSTANCE.convertDpToPixel(context, 200)
        }
    }

    fun setOnItemSelected(listener: (String) -> Unit) {
        this.listener = object: DialogOnItemSelectedListener {
            override fun onItemSelected(key: String) {
                listener(key)
            }
        }
    }

    interface DialogOnItemSelectedListener {
        fun onItemSelected(key : String)
    }
}