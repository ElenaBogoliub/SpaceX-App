package com.ebogoliub.ui.base.content

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.core.widget.TextViewCompat
import com.ebogoliub.ui.base.R
import com.ebogoliub.ui.base.databinding.ViewEmptyBinding

class EmptyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = R.attr.emptyViewStyle,
    defStyleRes: Int = R.style.Widget_App_EmptyView
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding: ViewEmptyBinding

    var state: EmptyState? = null
        set(value) {
            field = value
            if (value != null) {
                renderState(value)
            }
        }

    var onButtonClick: OnButtonClickListener = {}

    init {
        orientation = VERTICAL
        binding = ViewEmptyBinding.inflate(LayoutInflater.from(context), this)

        val a = context.obtainStyledAttributes(attrs, R.styleable.EmptyView, defStyleAttr, defStyleRes)
        val text = a.getString(R.styleable.EmptyView_android_text)
        val buttonText = a.getString(R.styleable.EmptyView_buttonText)
        val textAppearance = a.getResourceId(R.styleable.EmptyView_android_textAppearance, 0)
        val buttonTextAppearance = a.getResourceId(R.styleable.EmptyView_buttonTextAppearance, 0)
        val showButton = a.getBoolean(R.styleable.EmptyView_showButton, true)
        a.recycle()

        TextViewCompat.setTextAppearance(binding.message, textAppearance)
        binding.message.text = text

        TextViewCompat.setTextAppearance(binding.button1, buttonTextAppearance)
        binding.button1.text = buttonText
        binding.button1.isVisible = showButton
        binding.button1.setOnClickListener { onButtonClick() }
    }

    private fun renderState(state: EmptyState) {
        when (state) {
            is EmptyState.Message -> {
                binding.message.text = state.message.getText(binding.root.context)
                binding.button1.isVisible = false
            }

            is EmptyState.MessageWithButton -> {
                binding.message.text = state.message.getText(binding.root.context)
                binding.button1.text = state.buttonText.getText(binding.root.context)

                binding.button1.isVisible = true
            }
        }
    }
}

typealias OnButtonClickListener = () -> Unit