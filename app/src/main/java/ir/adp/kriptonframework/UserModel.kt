package ir.adp.kriptonframework

import com.google.gson.annotations.SerializedName


class UserModel(
    @SerializedName("name") var name: String? = null,
    @SerializedName("age") var age: Int = 0,
    @SerializedName("city") var city: String? = null
)