package site.golock.sm_crew.util

import android.Manifest.permission.*
import com.google.android.gms.maps.model.LatLng

object Constant {
     const val Interval: Long = 10000
     const val Fastest: Long = 5000
     const val ReqGPSCode: Int = 77
     const val ReqPermsCode: Int = 78
     const val BASE_URL : String = "https://web.go-lock.site/"
     const val IMG_URL : String = "${BASE_URL}assets/images/report/"
     const val CRW_URL : String = "${BASE_URL}assets/images/petugas/"
     const val KEY_ROUTE : String = "AIzaSyDtezUGGbDDzTEIpZANMjFT28As815T_f4"
     val Permission: Array<String> = arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION, INTERNET)
     val clr = arrayOf("#74b9ff", "#ff7675", "#55efc4", "#a29bfe", "#81ecec")
     val ctr : LatLng = LatLng(0.4859827,101.4418594)
     val bulan = arrayOf("Januari", "Pebruari", "Maret", "April", "Mei", "Juni", "Juli",
                                      "Agustus", "September", "Oktober", "Nopember", "Desember")
     //val clr2 = arrayOf("#74b9ff", "#b2bec3", "#b2bec3", "#b2bec3", "#b2bec3")
     //val Destination = LatLng(0.4925222, 101.4162749)
     //val Origin = LatLng(0.516191, 101.4607011)
     //val clrs = arrayOf("#7fff7272", "#7f31c7c5", "#7fff8a00")
}