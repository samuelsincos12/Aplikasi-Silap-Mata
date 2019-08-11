package site.golock.sm_crew.model

import com.google.gson.annotations.SerializedName

data class RLog (
    @SerializedName("error") val error : Boolean,
    @SerializedName("driver") val driver: MLog,
    @SerializedName("message") val message: String
)