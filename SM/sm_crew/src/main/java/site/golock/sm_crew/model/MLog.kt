package site.golock.sm_crew.model

import com.google.gson.annotations.SerializedName

data class MLog (
    @SerializedName("id_petugas") val idp : Int,
    @SerializedName("name") val nama : String,
    @SerializedName("foto") val photo : String,
    @SerializedName("level") val level : Int
)