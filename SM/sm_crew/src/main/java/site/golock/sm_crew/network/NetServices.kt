package site.golock.sm_crew.network

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import site.golock.sm_crew.model.RLog
import site.golock.sm_crew.model.RPeta
import site.golock.sm_crew.model.RVers

interface NetServices {

    @GET("marker")
    fun mPeta(): Call<RPeta>

    @FormUrlEncoded
    @POST("log")
    fun mLog(
        @Field("user") user: String,
        @Field("pass") pass: String
    ): Call<RLog>

    @FormUrlEncoded
    @POST("onr")
    fun mOn(
        @Field("id_petugas") idp: Int
    ): Call<RLog>

    @FormUrlEncoded
    @POST("offr")
    fun mOff(
        @Field("id_petugas") idp: Int
    ): Call<RLog>

    @FormUrlEncoded
    @POST("vers")
    fun mVer(
        @Field("id_petugas") idp: Int,
        @Field("id_laporan") idl: Int,
        @Field("status") stt: Int
    ): Call<RVers>
}
