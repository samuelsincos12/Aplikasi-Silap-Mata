package site.golock.sm_crew.model

import com.google.gson.annotations.SerializedName

data class RVers (
    @SerializedName("error") val error: Boolean,
    @SerializedName("message") val message: String
)