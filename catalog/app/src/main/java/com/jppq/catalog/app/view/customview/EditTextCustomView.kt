package com.jppq.catalog.app.view.customview

import android.content.Context
import android.graphics.Typeface
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import com.jppq.catalog.R
import com.jppq.catalog.databinding.LayoutEdittextBinding

class EditTextCustomView(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {

    var isError: Boolean = false
        private set
    private var isRequired = false

    var onTextChange: (() -> Unit)? = null

    private val binding =
        LayoutEdittextBinding.inflate(
            LayoutInflater.from(context),
            this
        )

    init {
        if (attrs != null) {
            val attributes =
                context.obtainStyledAttributes(attrs, R.styleable.EditTextCustomView)

            binding.apply {
                isRequired = attributes.getBoolean(R.styleable.EditTextCustomView_required, false)
                editText.setText(attributes.getString(R.styleable.EditTextCustomView_text))
                editText.hint = attributes.getString(R.styleable.EditTextCustomView_hint)
                editText.inputType = attributes.getInt(
                    R.styleable.EditTextCustomView_android_inputType,
                    InputType.TYPE_NULL
                )

                editText.typeface = Typeface.DEFAULT
            }

            attributes.recycle()
        }
    }

    fun setupView(
        onTextChange: (() -> Unit)? = null
    ) {
        this.onTextChange = onTextChange

        setNormalView()

        binding.apply {
            editText.isFocusable = true
            editText.isClickable = true

            onTextChangeListener {
                validateAll()
                onTextChange?.invoke()
            }

            setListenerEditTextChangeFocus()
        }
    }

    private fun isRequiredAndEmpty(): Boolean {
        return isRequired && binding.editText.text.isEmpty()
    }

    fun getText() = binding.editText.text.toString()

    fun validateAll() {
        when {
            isRequiredAndEmpty() -> {
                setErrorView()
            }
            else -> {
                setNormalView()
            }
        }
    }

    fun setErrorView() {
        binding.apply {
            editText.background =
                ContextCompat.getDrawable(context, R.drawable.bg_input_error)
        }
        isError = true
    }

    fun setNormalView() {
        binding.apply {
            editText.background =
                ContextCompat.getDrawable(context, R.drawable.bg_input)
        }
        isError = false
    }

    private fun onTextChangeListener(onChange: ((text: String) -> Unit)? = null) {
        binding.apply {
            editText.doAfterTextChanged {
                onChange?.invoke(editText.text.toString())
            }
        }
    }

    private fun setListenerEditTextChangeFocus() {
        binding.apply {
            editText.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
                if (!isError) {
                    editText.background =
                        ContextCompat.getDrawable(context, R.drawable.bg_input)
                }
            }
        }
    }
}