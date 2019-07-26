package ir.adp.framework.data.manager

import android.content.Context
import java.util.*

class Module {

    companion object {

        fun changeAppDirection(context: Context, direction: String) {
            val configuration = context.resources.configuration
            if (direction == "rtl") {
                configuration.setLayoutDirection(Locale("fa"))
            } else {
                configuration.setLayoutDirection(Locale.ENGLISH)
            }
            context.resources.updateConfiguration(configuration, context.resources.displayMetrics)
        }

    }
}