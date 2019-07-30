package ir.adp.kriptonframework

import com.google.gson.annotations.SerializedName
import io.reactivex.Observable
import ir.adp.framework.data.api.SimpleResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

class MainResponse(
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: Int
) : SimpleResponse() {

    companion object {
        const val TYPE_USER = 1
        const val TYPE_FRIEND = 2
    }

}

class MainRequest(
    @SerializedName("name") val name: String
)

interface MainService {

    @GET("ali/api.json")
    fun getData(): Observable<Response<List<MainResponse>>>

    @POST("ali/get.json")
    fun sendData(
        @Body request: MainRequest
    ): Observable<Response<MainResponse>>

}