package site.golock.sm_user.util

import android.content.Context
import android.location.Geocoder
import android.net.ConnectivityManager
import android.support.v7.app.AlertDialog
import android.widget.TextView
import site.golock.sm_user.R
import java.util.*

object Utils {

    fun checkConnect(ctx: Context): Boolean {
        (ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).also {
            val ni = it.activeNetworkInfo
            return ni != null && ni.isConnected && ni.isAvailable
        }
    }

    fun iAlt(ctx: Context, a: Double, b: Double, c: Int): String {
        Geocoder(ctx, Locale.getDefault()).getFromLocation(a, b, 1).also { ad->
            var mAlt: String? = null
            when (c) {
                0 -> { mAlt = ad[0].getAddressLine(0) }
                1 -> { mAlt = ad[0].featureName }
                2 -> { mAlt = ad[0].locality }
                3 -> { mAlt = ad[0].subAdminArea }
                4 -> { mAlt = ad[0].postalCode }
                5 -> { mAlt = ad[0].adminArea }
                6 -> { mAlt = ad[0].countryName }
            }
            return mAlt!!
        }
    }

    fun showJns(ctx: Context, j: TextView) {
        AlertDialog.Builder(ctx).also {
            it.setTitle(R.string.zJenis)
            it.setItems(Constant.Jenis) { p0, p1 ->
                j.text = Constant.Jenis[p1]
                p0.dismiss()
            }
            it.create()
            it.show()
        }
    }

    /*
    fun setWindowFlag(ctx: Context, a: Int, b: Boolean) {
        (ctx as Activity).window.also { win->
            win.attributes.also { wip->
                if (b) { wip.flags = wip.flags or a } else { wip.flags = wip.flags and a }
                win.attributes = wip
            }
        }
    }
    */
}