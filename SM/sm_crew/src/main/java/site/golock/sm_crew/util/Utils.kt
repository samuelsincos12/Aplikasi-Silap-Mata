package site.golock.sm_crew.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Geocoder
import android.net.ConnectivityManager
import android.support.v7.widget.CardView
import android.view.View
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import java.net.URL
import java.util.*

object Utils {

    fun checkConnect(ctx: Context): Boolean {
        (ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).also {
            val ni = it.activeNetworkInfo
            return ni != null && ni.isConnected
        }
    }

    fun visGO(
        c1: CardView,
        c2: View,
        c3: View,
        c4: View,
        c5: View,
        c6: View,
        r1: Int,
        r2: Int,
        r3: Int,
        r4: Int,
        r5: Int,
        r6: Int
    ) {
        when (r1) {
            1 -> { c1.visibility = View.VISIBLE }
            2 -> { c1.visibility = View.GONE }
        }
        when (r2) {
            1 -> { c2.visibility = View.VISIBLE }
            2 -> { c2.visibility = View.GONE }
        }
        when (r3) {
            1 -> { c3.visibility = View.VISIBLE }
            2 -> { c3.visibility = View.GONE }
        }
        when (r4) {
            1 -> { c4.visibility = View.VISIBLE }
            2 -> { c4.visibility = View.GONE }
        }
        when (r5) {
            1 -> { c5.visibility = View.VISIBLE }
            2 -> { c5.visibility = View.GONE }
        }
        when (r6) {
            1 -> { c6.visibility = View.VISIBLE }
            2 -> { c6.visibility = View.GONE }
        }
    }

    private fun mDate(t : String): String {
        when (t) {
            "01" -> { return Constant.bulan[0] }
            "02" -> { return Constant.bulan[1] }
            "03" -> { return Constant.bulan[2] }
            "04" -> { return Constant.bulan[3] }
            "05" -> { return Constant.bulan[4] }
            "06" -> { return Constant.bulan[5] }
            "07" -> { return Constant.bulan[6] }
            "08" -> { return Constant.bulan[7] }
            "09" -> { return Constant.bulan[8] }
            "10" -> { return Constant.bulan[9] }
            "11" -> { return Constant.bulan[10] }
            "12" -> { return Constant.bulan[11] }
        }
        return t
    }

    fun iDate (t : String): String {
        t.split("-").toTypedArray().also {
            return it[2] + " " + mDate(it[1]) + " " + it[0]
        }
    }

    fun iAlt(ctx: Context, a : Double, b : Double) : String? {
        Geocoder(ctx, Locale.getDefault()).getFromLocation(a, b, 1).also {
            return it[0].getAddressLine(0)
        }
    }

    fun bDFA(ctx: Context, a: String): BitmapDescriptor {
        val x = BitmapFactory.decodeStream(ctx.assets.open(a))
        val y = Bitmap.createScaledBitmap(x, 100, 100, false)
        return BitmapDescriptorFactory.fromBitmap(y)
    }

    fun bDFU(ctx: Context, a: String): BitmapDescriptor {
        URL(a).openConnection().also {
            it.doInput = true
            it.connect()
            val x = BitmapFactory.decodeStream(it.getInputStream())
            val y = Bitmap.createScaledBitmap(x, 100, 100, false)
            return BitmapDescriptorFactory.fromBitmap(y)
        }
    }

    //Spherical Law of Cosine
    fun slcd(startLat: Double, startLong: Double, endLat: Double, endLong: Double): Double {
        val dLon = Math.toRadians(endLong - startLong)

        val lat1 = Math.toRadians(startLat)
        val lat2 = Math.toRadians(endLat)

        return Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(dLon)) * 6371
    }

    /*
    //Ray_Casting
    private fun isPointInPolygon(tap: LatLng, vertices: ArrayList<LatLng>): Boolean {
        var intersectCount = 0
        for (j in vertices.indices - 1) {
            if (rayCastIntersect(tap, vertices[j], vertices[j + 1])) {
                intersectCount++
            }
        }
        return intersectCount % 2 == 1 // odd = inside, even = outside;
    }

    private fun rayCastIntersect(tap: LatLng, vertA: LatLng, vertB: LatLng): Boolean {
        val aY = vertA.latitude
        val bY = vertB.latitude
        val aX = vertA.longitude
        val bX = vertB.longitude
        val pY = tap.latitude
        val pX = tap.longitude
        if (aY > pY && bY > pY || aY < pY && bY < pY || aX < pX && bX < pX) {
            return false // a and b can't both be above or below pt.y, and a or // b must be east of pt.x
        }
        val m = (aY - bY) / (aX - bX) // Rise over run
        val bee = -aX * m + aY // y = mx + b
        val x = (pY - bee) / m // algebra is neat!
        return x > pX
    }

    //Haversine Formula
    fun hvrd(startLat: Double, startLong: Double, endLat: Double, endLong: Double): Double {
        var startLat = startLat
        var endLat = endLat

        val dLat = Math.toRadians(endLat - startLat)
        val dLong = Math.toRadians(endLong - startLong)

        startLat = Math.toRadians(startLat)
        endLat = Math.toRadians(endLat)

        val a = Math.pow(Math.sin(dLat/2), 2.0)+Math.cos(startLat)*Math.cos(endLat)*Math.pow(Math.sin(dLong/2), 2.0)
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))

        return 6371 * c
    }

     fun deleteCache(ctx: Context) {
        try {
            deleteDir(ctx.cacheDir)
        } catch (e: IOException) { e.printStackTrace()}
    }

    private fun deleteDir(dir: File?): Boolean {
        if (dir != null && dir.isDirectory) {
            val children = dir.list()
            for (i in children.indices) {
                val success = deleteDir(File(dir, children[i]))
                if (!success) {
                    return false
                }
            }
            return dir.delete()
        } else if(dir!= null && dir.isFile) {
            return dir.delete()
        } else {
            return false
        }
    }

    fun bDFV(ctx : Context, vi : Int) : BitmapDescriptor {
        val vd = ContextCompat.getDrawable(ctx, vi)!!
        vd.setBounds(0, 0, vd.intrinsicHeight, vd.intrinsicWidth)
        val bm = Bitmap.createBitmap(vd.intrinsicWidth, vd.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val cv = Canvas(bm)
        vd.draw(cv)
        return BitmapDescriptorFactory.fromBitmap(bm)
    }
    */
}