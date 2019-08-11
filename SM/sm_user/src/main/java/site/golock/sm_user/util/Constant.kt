package site.golock.sm_user.util

import android.Manifest.permission.*
import com.google.android.gms.maps.model.LatLng

object Constant {
    const val BASE_URL : String = "https://web.go-lock.site/"
    const val Interval: Long = 10000
    const val Fastest: Long = 5000
    const val ReqCamCode: Int = 76
    const val ReqGPSCode: Int = 77
    const val ReqPermsCode: Int = 78
    val cCtr: LatLng = LatLng(-2.1358819, 120.7639455)
    val Permission: Array<String> = arrayOf(ACCESS_FINE_LOCATION,
                                            ACCESS_COARSE_LOCATION,
                                            CAMERA,
                                            INTERNET,
                                            READ_EXTERNAL_STORAGE,
                                            WRITE_EXTERNAL_STORAGE)
    val Jenis: Array<String> = arrayOf("Dilarang Berhenti",
                                       "Dilarang Masuk",
                                       "Dilarang Parkir",
                                       "Dilarang Belok")
}