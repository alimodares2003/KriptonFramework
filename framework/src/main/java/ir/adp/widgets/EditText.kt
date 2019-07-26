package ir.adp.widgets

import android.content.Context
import android.graphics.Typeface
import android.text.InputType
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import ir.adp.framework.R


/**
 * Created by Ali on 6/3/2019.
 */

class EditText : TextInputLayout {

    companion object {
        const val INPUT_TYPE_DEFAULT = 0
        const val INPUT_TYPE_EMAIL = 1
        const val INPUT_TYPE_PASSWORD = 2
        const val INPUT_TYPE_PHONE = 3
        const val INPUT_TYPE_NUMBER_DECIMAL = 4
        const val INPUT_TYPE_MULTI_LINE = 5
        const val INPUT_TYPE_NUMBER_FLOAT = 6
        const val INPUT_TYPE_WEBSITE = 7
        const val INPUT_TYPE_PERSON_NAME = 8

        private fun createInputType(inputType: Int): Int {
            return when (inputType) {
                INPUT_TYPE_DEFAULT -> InputType.TYPE_CLASS_TEXT
                INPUT_TYPE_EMAIL -> InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                INPUT_TYPE_PASSWORD -> InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                INPUT_TYPE_PHONE -> InputType.TYPE_CLASS_PHONE
                INPUT_TYPE_NUMBER_DECIMAL -> InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_SIGNED
                INPUT_TYPE_MULTI_LINE -> InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_MULTI_LINE
                INPUT_TYPE_NUMBER_FLOAT -> InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
                INPUT_TYPE_WEBSITE -> InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS
                INPUT_TYPE_PERSON_NAME -> InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PERSON_NAME
                else -> InputType.TYPE_CLASS_TEXT
            }
        }
    }


    val editText = TextInputEditText(context)
    var inputType = INPUT_TYPE_DEFAULT

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        initView(attrs)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(attrs)
    }

    constructor(context: Context) : super(context) {
//        initView()
    }

    private fun initView(attrs: AttributeSet) {
        val typeface = Typeface.createFromAsset(context.assets, context.getString(R.string.font_mainRegular))
        setTypeface(typeface)
        editText.typeface = typeface
        editText.textSize = 14f

        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.EditText, 0, 0)
        inputType = a.getColor(R.styleable.EditText_inputType, inputType)

        editText.inputType = createInputType(this.inputType)
        addView(editText)
    }

    fun getText(): String {
        return editText.text.toString()
    }

    fun setText(text: String) {
        editText.setText(text)
    }
}