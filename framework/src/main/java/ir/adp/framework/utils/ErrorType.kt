package ir.adp.framework.utils

import ir.adp.framework.R
import ir.adp.framework.utils.model.ErrorViewModel


object ErrorType {
    val ERROR_EMPTY_DEFAULT = ErrorViewModel(R.drawable.ic_error, R.string.noItem, R.string.noItem_description)
    val ERROR_EMPTY_CUSTOM = ErrorViewModel(R.drawable.ic_warning, R.string.noItem, R.string.noItem_description)
}
