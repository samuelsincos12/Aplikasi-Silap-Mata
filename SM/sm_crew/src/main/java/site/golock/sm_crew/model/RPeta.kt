package site.golock.sm_crew.model

import com.google.gson.annotations.SerializedName

data class RPeta (
    @SerializedName("error") val error: Boolean,
    @SerializedName("marker") val data: List<MPeta>,
    @SerializedName("message") val message: String
)