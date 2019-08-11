package site.golock.sm_crew.model

import com.google.gson.annotations.SerializedName

data class MPeta(
    @SerializedName("id_laporan") val idl: Int,
    @SerializedName("gambar") val img: String,
    @SerializedName("jns_pelanggaran") val jns: String,
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double,
    @SerializedName("alamat") val alm: String,
    @SerializedName("keterangan") val ket: String,
    @SerializedName("tanggal") val tgl: String,
    @SerializedName("waktu") val jam: String,
    @SerializedName("status") val stt: Int
)