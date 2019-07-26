package ir.adp.framework.data.api

import com.google.gson.annotations.SerializedName

open class SimpleResponse(
    @SerializedName("status") var status: Int = 0,
    @SerializedName("message") var message: String? = null,
    @SerializedName("body") var body: String? = null
)