package site.golock.sm_user.model

import com.google.gson.annotations.SerializedName

data class SReport (
    @SerializedName("error") val error: Boolean,
    @SerializedName("message") val message: String
)