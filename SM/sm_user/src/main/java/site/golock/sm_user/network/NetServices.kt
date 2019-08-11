package site.golock.sm_user.network

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import site.golock.sm_user.model.SReport

interface NetServices {

    @Multipart
    @POST("rept")
    fun mRep(
        @Part img: MultipartBody.Part,
        @Part("jns") jns: RequestBody,
        @Part("lat") lat: RequestBody,
        @Part("lon") lon: RequestBody,
        @Part("alamat") alamat: RequestBody,
        @Part("ket") ket: RequestBody
    ): Call<SReport>
}