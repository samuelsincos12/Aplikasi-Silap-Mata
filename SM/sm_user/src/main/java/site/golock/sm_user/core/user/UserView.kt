package site.golock.sm_user.core.user

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.graphics.Typeface
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.ActivityCompat
import android.support.v4.app.FragmentActivity
import android.support.v4.widget.CircularProgressDrawable
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.*
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.*
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import site.golock.sm_user.R
import site.golock.sm_user.util.Constant
import site.golock.sm_user.util.Constant.Permission
import site.golock.sm_user.util.Constant.ReqCamCode
import site.golock.sm_user.util.Constant.ReqGPSCode
import site.golock.sm_user.util.Constant.ReqPermsCode
import site.golock.sm_user.util.Utils
import java.io.File

class UserView: FragmentActivity(), UserContract.View, OnMapReadyCallback, OnMapClickListener, OnMarkerClickListener {

    @BindView(R.id.tKet) lateinit var tKet: EditText
    @BindView(R.id.vFab) lateinit var vFab: FrameLayout
    @BindView(R.id.vImg) lateinit var vImg: ImageView
    @BindView(R.id.pBar) lateinit var pBar: ProgressBar
    @BindView(R.id.tAlt) lateinit var tAlt: TextView
    @BindView(R.id.tJns) lateinit var tJns: TextView
    @BindView(R.id.tTle) lateinit var tTle: TextView
    @BindView(R.id.tStt) lateinit var tStt: TextView
    @BindView(R.id.tBar) lateinit var tBar: Toolbar
    @BindView(R.id.vMrk) lateinit var vMrk: View
    @BindView(R.id.vRep) lateinit var vRep: View
    @BindView(R.id.vSht) lateinit var vSht: View

    private lateinit var userPres: UserPresenter
    private lateinit var mSht: BottomSheetBehavior<View>
    private lateinit var mCam: CameraPosition
    private lateinit var mMap: GoogleMap
    private lateinit var mLoc: Location
    private lateinit var mLoR: LocationRequest
    private lateinit var mLSR: LocationSettingsRequest
    private lateinit var mSCl: SettingsClient

    private var mFLP: FusedLocationProviderClient? = null
    private var mBPS: Boolean = false
    private var mLat: Double? = null
    private var mLon: Double? = null
    private var mMrk: Marker? = null

    override lateinit var presenter: UserContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        ButterKnife.bind(this)
        userPres = UserPresenter(this)

        (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment).getMapAsync(this)

        mSht = BottomSheetBehavior.from(vSht)
        mSht.also {
            it.state = BottomSheetBehavior.STATE_HIDDEN
            it.isHideable = true
            it.setBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    vFab.animate().scaleX(1 - slideOffset).scaleY(1 - slideOffset).setDuration(0).start()
                }

                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    when (newState) {
                        BottomSheetBehavior.STATE_EXPANDED -> {
                            vFab.animate().scaleX(0f).scaleY(0f).setDuration(300).start()
                        }
                        BottomSheetBehavior.STATE_COLLAPSED -> {
                            vFab.animate().scaleX(0f).scaleY(0f).setDuration(300).start()
                        }
                        BottomSheetBehavior.STATE_HIDDEN -> {
                            vFab.animate().scaleX(1f).scaleY(1f).setDuration(300).start()
                        }
                    }
                }
            })
        }
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        mCam = CameraPosition.builder().target(Constant.cCtr).zoom(3f).bearing(0f).build()
        mMap.also {
            it.mapType = MAP_TYPE_NORMAL
            it.isTrafficEnabled
            it.animateCamera(CameraUpdateFactory.newCameraPosition(mCam))
            it.setOnMarkerClickListener(this)
            it.setOnMapClickListener(this)
        }

        mLoR = LocationRequest().also {
            it.interval = Constant.Interval
            it.fastestInterval = Constant.Fastest
            it.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        mFLP = LocationServices.getFusedLocationProviderClient(this)
        mSCl = LocationServices.getSettingsClient(this)

        LocationSettingsRequest.Builder().addLocationRequest(mLoR).also { mLSR = it.build() }

        if (presenter.cekPermission(this) && mSCl.checkLocationSettings(mLSR).isSuccessful) {
            actFus()
        } else {
            ActivityCompat.requestPermissions(this, Permission, ReqPermsCode)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        presenter.reqPermission(requestCode, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            ReqCamCode -> {
                actFus()
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        overridePendingTransition(R.anim.slide_down, R.anim.slide_bottom)
                        vMrk.visibility = View.GONE.apply { vRep.visibility = View.VISIBLE }
                        mSht.also {
                            it.state = BottomSheetBehavior.STATE_EXPANDED
                            it.isHideable
                        }

                        val vCPD = CircularProgressDrawable(this).also {
                            it.strokeWidth = 5f
                            it.centerRadius = 30f
                            it.start()
                        }
                        Glide.with(this)
                             .load(presenter.uPic)
                             .apply(RequestOptions()
                                 .placeholder(vCPD)
                                 .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                             .into(vImg)

                        tBar.setNavigationOnClickListener {
                            vMrk.visibility = View.VISIBLE.apply { vRep.visibility = View.GONE }
                            tKet.text.clear()
                            mSht.also {
                                it.state = BottomSheetBehavior.STATE_HIDDEN
                                it.isHideable = true
                            }
                        }
                    }
                    Activity.RESULT_CANCELED -> {
                        overridePendingTransition(R.anim.slide_down, R.anim.slide_bottom)
                        vMrk.visibility = View.GONE.apply { vRep.visibility = View.VISIBLE }
                        mSht.also {
                            it.state = BottomSheetBehavior.STATE_HIDDEN
                            it.isHideable = true
                        }
                    }
                }
            }
            ReqGPSCode -> {
                when (resultCode) {
                    Activity.RESULT_OK -> { actFus() }
                    Activity.RESULT_CANCELED -> {
                        tStt.setText(R.string.zGPS)
                        tStt.setTypeface(Typeface.DEFAULT, Typeface.ITALIC)
                        Handler().postDelayed({
                            tStt.setText(R.string.zTitle)
                            tStt.setTypeface(Typeface.DEFAULT, Typeface.BOLD)
                            cekGPS()
                        }, 3000)
                    }
                }
            }
        }
    }

    override fun onMapClick(p0: LatLng) {
        vMrk.visibility = View.VISIBLE.apply { vRep.visibility = View.GONE }
        mSht.also {
            it.state = BottomSheetBehavior.STATE_HIDDEN
            it.isHideable = true
        }
    }

    override fun onMarkerClick(p0: Marker): Boolean {
        vMrk.visibility = View.VISIBLE.apply { vRep.visibility = View.GONE }
        mSht.also {
            it.state = BottomSheetBehavior.STATE_EXPANDED
            it.isHideable
        }

        tTle.text = p0.title
        tAlt.text = p0.snippet
        return true
    }

    override fun onPause() {
        super.onPause()
        tKet.text.clear()
        if (mFLP != null) mFLP!!.removeLocationUpdates(mLoB)
    }

    override fun onStop() {
        super.onStop()
        tKet.text.clear()
        mFLP!!.removeLocationUpdates(mLoB)
    }

    override fun onBackPressed() {
        if (mBPS) super.onBackPressed()
        mBPS = true
        tStt.setText(R.string.zBack)
        tStt.setTypeface(Typeface.DEFAULT, Typeface.ITALIC)
        tKet.text.clear()
        Handler().postDelayed({
            tStt.setText(R.string.zTitle)
            tStt.setTypeface(Typeface.DEFAULT, Typeface.BOLD)
        }, 3000)

        vMrk.visibility = View.VISIBLE.apply { vRep.visibility = View.GONE }
        mSht.also {
            it.state = BottomSheetBehavior.STATE_HIDDEN
            it.isHideable = true
        }
        Handler().postDelayed({ mBPS = false }, 2000)
    }

    override fun actReqFal() {
        tStt.setText(R.string.zPermission)
        tStt.setTypeface(Typeface.DEFAULT, Typeface.ITALIC)
        Handler().postDelayed({
            tStt.setText(R.string.zTitle)
            tStt.setTypeface(Typeface.DEFAULT, Typeface.BOLD)
            ActivityCompat.requestPermissions(this, Permission, ReqPermsCode)
        }, 3000)
    }

    override fun cekGPS() {
        mSCl.checkLocationSettings(mLSR)
            .addOnSuccessListener { actFus() }
            .addOnFailureListener { fl ->
                when (((fl as ApiException).statusCode)) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                        try {
                            (fl as ResolvableApiException).startResolutionForResult(this, ReqGPSCode)
                        } catch (sie: IntentSender.SendIntentException) {
                            Log.i("GPS", "Tidak dapat menjalankan permintaan")
                        }
                    }
                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                        Log.i("GPS", "Pengaturan lokasi tidak memadai")
                    }
                }
            }
    }

    @SuppressLint("MissingPermission")
    override fun actFus() {
        mFLP!!.requestLocationUpdates(mLoR, mLoB, Looper.myLooper()).apply { mMap.isMyLocationEnabled }
    }

    override fun actRes(a: String) {
        pBar.visibility = View.GONE
        tStt.text = a
        tStt.setTypeface(Typeface.DEFAULT, Typeface.ITALIC)
        Handler().postDelayed({
            tStt.setText(R.string.zTitle)
            tStt.setTypeface(Typeface.DEFAULT, Typeface.BOLD)
        }, 4000)
    }

    override fun actFail() {
        pBar.visibility = View.GONE
        tStt.setText(R.string.zError)
        tStt.setTypeface(Typeface.DEFAULT, Typeface.ITALIC)
        Handler().postDelayed({
            tStt.setText(R.string.zTitle)
            tStt.setTypeface(Typeface.DEFAULT, Typeface.BOLD)
        }, 4000)
    }

    @OnClick(R.id.vFab) fun onCamera() {
        presenter.reqCamera(this)
        overridePendingTransition(R.anim.slide_top, R.anim.slide_bottom)
    }

    @OnClick(R.id.bLap) fun onCamera2() {
        presenter.reqCamera(this)
        overridePendingTransition(R.anim.slide_top, R.anim.slide_bottom)
    }

    @OnClick(R.id.tJns) fun onJns() { Utils.showJns(this, tJns) }

    @OnClick(R.id.bRep) fun onUpload() {
        val c0 = File(presenter.uPic.path)
        val c1 = tJns.text.toString().trim()
        val c2 = mLat.toString()
        val c3 = mLon.toString()
        val c4 = Utils.iAlt(this, mLat!!, mLon!!, 0)
        val c5 = tKet.text.toString().trim()

        presenter.resLap(this, c0, c1, c2, c3, c4, c5)
        pBar.visibility = View.VISIBLE
        mSht.also {
            it.state = BottomSheetBehavior.STATE_HIDDEN
            it.isHideable = true
        }
    }

    private val mLoB = object: LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            if (p0.locations.size > 0) {
                mLoc = p0.locations[p0.locations.size - 1]
                if (mMrk != null) mMrk!!.remove()
                mLat = mLoc.latitude
                mLon = mLoc.longitude

                val mP = LatLng(mLat!!, mLon!!)
                MarkerOptions().also {
                    it.position(mP)
                    it.title(Utils.iAlt(this@UserView, mLat!!, mLon!!, 2))
                    it.snippet(Utils.iAlt(this@UserView, mLat!!, mLon!!, 0))
                    it.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))

                    mMrk = mMap.addMarker(it)
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mP, 16f))
                }
            }
        }
    }
}