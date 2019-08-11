package site.golock.sm_crew.core.crew

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.ActivityCompat
import android.support.v4.app.FragmentActivity
import android.support.v4.widget.CircularProgressDrawable
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AlertDialog
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.*
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnCheckedChanged
import butterknife.OnClick
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.directions.route.*
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.*
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import de.hdodenhof.circleimageview.CircleImageView
import site.golock.sm_crew.R
import site.golock.sm_crew.model.MPeta
import site.golock.sm_crew.core.login.LoginView
import site.golock.sm_crew.util.*
import site.golock.sm_crew.util.Constant.CRW_URL
import site.golock.sm_crew.util.Constant.Fastest
import site.golock.sm_crew.util.Constant.IMG_URL
import site.golock.sm_crew.util.Constant.Interval
import site.golock.sm_crew.util.Constant.Permission
import site.golock.sm_crew.util.Constant.ReqGPSCode
import site.golock.sm_crew.util.Constant.ReqPermsCode
import site.golock.sm_crew.util.Constant.clr
import site.golock.sm_crew.util.Constant.ctr
import site.golock.sm_crew.util.Utils.slcd
import java.util.*
import kotlin.collections.ArrayList

class CrewView: FragmentActivity(), CrewContract.View, OnMapReadyCallback,
    OnMapClickListener, OnMarkerClickListener, OnPolylineClickListener, RAdapter.RAdapterCallback {

    @BindView(R.id.cDir) lateinit var cDir : CardView
    @BindView(R.id.cTol) lateinit var cTol : CardView
    @BindView(R.id.cPfl) lateinit var cPfl : CircleImageView
    @BindView(R.id.cNav) lateinit var cNav : CircleImageView
    @BindView(R.id.cImP) lateinit var cImP : CircleImageView
    @BindView(R.id.vFra) lateinit var vFab : FrameLayout
    @BindView(R.id.vImg) lateinit var vImg : ImageView
    @BindView(R.id.vIme) lateinit var vIme : ImageView
    @BindView(R.id.pBar) lateinit var pBar : ProgressBar
    @BindView(R.id.vRec) lateinit var vRec : RecyclerView
    @BindView(R.id.vSwh) lateinit var vSwh : Switch
    @BindView(R.id.vSwR) lateinit var vSwR : SwipeRefreshLayout
    @BindView(R.id.tJud) lateinit var tJud : TextView
    @BindView(R.id.tAlm) lateinit var tAlm : TextView
    @BindView(R.id.tFrm) lateinit var tFrm : TextView
    @BindView(R.id.tToo) lateinit var tToo : TextView
    @BindView(R.id.tJrk) lateinit var tJrk : TextView
    @BindView(R.id.tTmp) lateinit var tTmp : TextView
    @BindView(R.id.tStt) lateinit var tStt : TextView
    @BindView(R.id.tNam) lateinit var tNam : TextView
    @BindView(R.id.tLvl) lateinit var tLvl : TextView
    @BindView(R.id.tJen) lateinit var tJen : TextView
    @BindView(R.id.tAla) lateinit var tAla : TextView
    @BindView(R.id.tTgl) lateinit var tTgl : TextView
    @BindView(R.id.tWkt) lateinit var tWkt : TextView
    @BindView(R.id.tSat) lateinit var tSat : TextView
    @BindView(R.id.tKet) lateinit var tKet : TextView
    @BindView(R.id.bTre) lateinit var bTre : TextView
    @BindView(R.id.tBar) lateinit var tBar : Toolbar
    @BindView(R.id.bSht) lateinit var vSht : View
    @BindView(R.id.iMrk) lateinit var iMrk : View
    @BindView(R.id.iPfl) lateinit var iPfl : View
    @BindView(R.id.iRep) lateinit var iRep : View
    @BindView(R.id.iRot) lateinit var iRot : View
    @BindView(R.id.iDet) lateinit var iDet : View

    private lateinit var crewPres: CrewPresenter
    private lateinit var mSht : BottomSheetBehavior<View>
    private lateinit var mCap : CameraPosition
    private lateinit var mMap : GoogleMap
    private lateinit var mLoR : LocationRequest
    private lateinit var mLSR : LocationSettingsRequest
    private lateinit var mSCl : SettingsClient

    private var mRot : ArrayList<Route>? = null
    private var mPea : ArrayList<MPeta>? = null
    private var mBPS : Boolean = false
    private var mLat : Double? = null
    private var mLon : Double? = null
    private var mFLP : FusedLocationProviderClient? = null
    private var mPos : Int? = null
    private var mPot : LatLng? = null
    private var mBld : LatLngBounds.Builder? = null
    private var mPet : List<MPeta>? = null
    private var mMrk : Marker? = null

    override lateinit var presenter: CrewContract.Presenter

    override fun onStart() {
        super.onStart()
        cekStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        ButterKnife.bind(this)
        crewPres = CrewPresenter(this)

        (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment).getMapAsync(this)

        mSht = BottomSheetBehavior.from(vSht)
        mSht.also {
            it.state = BottomSheetBehavior.STATE_HIDDEN
            it.isHideable = true
            it.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback () {
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
                            mCap = CameraPosition.builder().target(ctr).zoom(11.5f).bearing(0f).build()
                            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(mCap), 1000, null)
                            vFab.animate().scaleX(1f).scaleY(1f).setDuration(300).start()
                        }
                    }
                }
            })
        }
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        mCap = CameraPosition.builder().target(ctr).zoom(11.5f).bearing(0f).build()
        mMap.also {
            it.mapType = MAP_TYPE_NORMAL
            it.isTrafficEnabled = true
            it.animateCamera(CameraUpdateFactory.newCameraPosition(mCap), 1000, null)
            it.setOnMapClickListener(this)
            it.setOnMarkerClickListener(this)
            it.setOnPolylineClickListener(this)
            it.uiSettings.isCompassEnabled = false
            it.clear()
        }

        mLoR = LocationRequest().also {
            it.interval = Interval
            it.fastestInterval = Fastest
            it.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        mFLP = LocationServices.getFusedLocationProviderClient(this)
        mSCl = LocationServices.getSettingsClient(this)

        LocationSettingsRequest.Builder().addLocationRequest(mLoR).also { mLSR = it.build() }

        if (presenter.cekPermission(this) && mSCl.checkLocationSettings(mLSR).isSuccessful) { actFus() }
        else { ActivityCompat.requestPermissions(this, Permission, ReqPermsCode) }

        sImg(CRW_URL + SharePref(this).getVS("foto"), cPfl)
        pBar.visibility = View.GONE
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        presenter.reqPermission(requestCode, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
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

    override fun onMapClick(p0: LatLng?) {
        cClose()
    }

    override fun onMarkerClick(p0: Marker): Boolean {
        Utils.visGO(cTol, iMrk, iPfl, iRot, iRep, iDet, 1, 1, 2, 2, 2, 2)
        mSht.also {
            it.state = BottomSheetBehavior.STATE_COLLAPSED
            it.peekHeight = 475
        }

        actFur()
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(p0.position, 16f))
        mPot = p0.position

        tJud.text = p0.title
        p0.snippet.split("&").toTypedArray().also {
            tAlm.text = it[1]
            if (it[0] == "zero") { Glide.with(this).load(R.drawable.map_marker_outline).into(vImg) }
            else { sImg(IMG_URL + it[0], vImg) }
        }
        return true
    }

    override fun onPolylineClick(p0: Polyline) {
        cDir.visibility = View.VISIBLE
        Utils.visGO(cTol, iMrk, iPfl, iRot, iRep, iDet, 2, 2, 2, 1, 2, 2)
        mSht.also {
            it.state = BottomSheetBehavior.STATE_COLLAPSED
            it.peekHeight = 220
        }

        for (i in mRot!!.indices) {
            if (p0.points == mRot!![i].points) {
                tFrm.text = Utils.iAlt(this, mLat!!, mLon!!)
                tToo.text = Utils.iAlt(this, mPot!!.latitude, mPot!!.longitude)
                tJrk.text = mRot!![i].distanceText
                tTmp.text = mRot!![i].durationText
                cNav.circleBackgroundColor = p0.color
                mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(mRot!![i].latLgnBounds, 100))
            }
        }
    }

    override fun onBackPressed() {
        if (mBPS) super.onBackPressed()
        mBPS = true
        txtBar(R.string.zBack)
        if (cDir.visibility == View.VISIBLE) {
            mMap.clear()
            cDir.visibility = View.GONE
        }
        cClose()
        Handler().postDelayed({ mBPS = false }, 2000)
    }

    override fun cekStart() {
        if(!SharePref(this).inLog("user")) {
            finish()
            startActivity(Intent(this@CrewView, LoginView::class.java))
        } else if (SharePref(this).getVI("fab") == 1) {
            vFab.visibility = View.GONE
            vSwh.isChecked = true
            vSwh.setText(R.string.zSOn)
        }
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
        if (vFab.visibility == View.GONE) { presenter.resPeta(this) }
    }

    override fun actFur() {
        mFLP!!.removeLocationUpdates(mLoB)
    }

    override fun actOnOff(a: String) {
        tStt.text = a
        tStt.setTypeface(Typeface.DEFAULT, Typeface.ITALIC)
        Handler().postDelayed({
            tStt.setText(R.string.zTitle)
            tStt.setTypeface(Typeface.DEFAULT, Typeface.BOLD)
        }, 3000)
    }

    override fun actRes(p0: List<MPeta>?) {
        mPet = p0
        pBar.visibility = View.VISIBLE
        Handler().postDelayed({
            vRec.also {
                it.adapter = RAdapter(mPet as ArrayList<MPeta>, mLat!!, mLon!!, this)
                it.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
            }

            if (!vSwR.isRefreshing) {
                vSwR.setOnRefreshListener {
                    vRec.also {
                        it.adapter = RAdapter(mPet as ArrayList<MPeta>, mLat!!, mLon!!, this)
                        it.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
                    }
                    vSwR.isRefreshing = false
                }
            }

            mBld = LatLngBounds.builder()
            for (i in mPet!!.indices) {
                if ((Math.round(slcd(mLat!!, mLon!!, mPet!![i].lat, mPet!![i].lon) * 100.0) / 100.0) <= 5.0) {
                    mPet!![i].jns.split(" ").toTypedArray().also {
                        mMap.addMarker(MarkerOptions()
                            .position(LatLng(mPet!![i].lat, mPet!![i].lon))
                            .icon(Utils.bDFA(this, "${it[0] + it[1] + mPet!![i].stt}.png"))
                            .title(mPet!![i].jns)
                            .snippet("${mPet!![i].img}&${mPet!![i].alm}"))
                    }
                    mBld!!.include(LatLng(mPet!![i].lat, mPet!![i].lon))
                } else {
                    mPet!![i].jns.split(" ").toTypedArray().also {
                        mMap.addMarker(MarkerOptions()
                            .position(LatLng(mPet!![i].lat, mPet!![i].lon))
                            .icon(Utils.bDFA(this, "${it[0] + it[1] + mPet!![i].stt}.png"))
                            .title(mPet!![i].jns)
                            .snippet("${mPet!![i].img}&${mPet!![i].alm}"))
                    }
                }
                mBld!!.include(LatLng(mLat!!, mLon!!))
            }
            pBar.visibility = View.GONE
        }, 3000)
    }

    override fun actFail() {
        pBar.visibility = View.GONE
        txtBar(R.string.zError)
    }

    override fun actRot(p0: ArrayList<Route>) {
        mMap.clear()
        cDir.visibility = View.VISIBLE
        Utils.visGO(cTol, iMrk, iPfl, iRot, iRep, iDet, 2, 2, 2, 1, 2, 2)
        mSht.also {
            it.state = BottomSheetBehavior.STATE_COLLAPSED
            it.peekHeight = 220
        }

        mRot = p0
        for (i in mRot!!.size - 1 downTo 0) {
            mMap.addPolyline(PolylineOptions()
                .width(20f)
                .color(Color.parseColor(clr[i % clr.size]))
                .pattern(Arrays.asList(Dot() as PatternItem, Dot() as PatternItem))
                .geodesic(true)
                .zIndex(3f - i)
                .clickable(true)
                .addAll(mRot!![i].points))
            tFrm.text = Utils.iAlt(this, mLat!!, mLon!!)
            tToo.text = Utils.iAlt(this, mPot!!.latitude, mPot!!.longitude)
            tJrk.text = mRot!![i].distanceText
            tTmp.text = mRot!![i].durationText
            cNav.circleBackgroundColor = Color.parseColor(clr[i % clr.size])
            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(mRot!![i].latLgnBounds, 100))
        }

        mMap.also {
            it.addMarker(MarkerOptions()
                .position(LatLng(mLat!!, mLon!!))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .title("Posisi Anda")
                .snippet("zero&${Utils.iAlt(this, mLat!!, mLon!!)}"))
            it.addMarker(MarkerOptions()
                .position(mPot!!)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))
                .title("Tujuan Anda")
                .snippet("zero&${Utils.iAlt(this, mPot!!.latitude, mPot!!.longitude)}"))
        }
    }

    override fun onMapListener(p0: ArrayList<MPeta>, i: Int) {
        vMarker(p0, i)
    }

    override fun onDetailListener(p0: ArrayList<MPeta>, i: Int) {
        Utils.visGO(cTol, iMrk, iPfl, iRot, iRep, iDet, 2, 2, 2, 2, 2, 1)
        mSht.also {
            it.state = BottomSheetBehavior.STATE_EXPANDED
        }
        mPea = p0
        mPos = i

        sImg(IMG_URL + p0[i].img, vIme)
        tJen.text = p0[i].jns
        tAla.text = p0[i].alm
        tTgl.text = Utils.iDate(p0[i].tgl)
        tWkt.text = p0[i].jam
        tKet.text = p0[i].ket

        when (p0[i].stt) {
            1 -> {
                tSat.setText(R.string.zLap1)
                bTre.visibility = View.VISIBLE
                bTre.setText(R.string.zTVer)
                bTre.background = Drawable.createFromXml(resources, resources.getXml(R.xml.ripple_main1))
                bTre.isClickable = true
            }
            2 -> {
                tSat.setText(R.string.zLap2)
                bTre.visibility = View.VISIBLE
                bTre.setText(R.string.zSSVer)
                bTre.background = Drawable.createFromXml(resources, resources.getXml(R.xml.ripple_main3))
                bTre.isClickable = false
            }
            3 -> {
                tSat.setText(R.string.zLap3)
                bTre.visibility = View.VISIBLE
                bTre.setText(R.string.zSSVer)
                bTre.background = Drawable.createFromXml(resources, resources.getXml(R.xml.ripple_main2))
                bTre.isClickable = false
            }
        }
    }

    @OnClick(R.id.bTre) fun vVers() {
        AlertDialog.Builder(this).also {
            it.setTitle(R.string.zTVer)
            it.setMessage(R.string.zMVer)
            it.setPositiveButton(R.string.zBVer) { p0, p1 ->
                pBar.visibility = View.VISIBLE
                presenter.resVer(this, SharePref(this).getVI("id")!!, mPea!![mPos!!].idl, 2)
                Utils.visGO(cTol, iMrk, iPfl, iRot, iRep, iDet, 1, 1, 2, 2, 2, 2)
                mSht.also { b->
                    b.state = BottomSheetBehavior.STATE_HIDDEN
                    b.isHideable
                }
                p0.dismiss().apply { p1.toByte() }
                onMapReady(mMap)
            }
            it.setNegativeButton(R.string.zSVer) { p0, p1 ->
                pBar.visibility = View.VISIBLE
                presenter.resVer(this, SharePref(this).getVI("id")!!, mPea!![mPos!!].idl, 3)
                Utils.visGO(cTol, iMrk, iPfl, iRot, iRep, iDet, 1, 1, 2, 2, 2, 2)
                mSht.also { b->
                    b.state = BottomSheetBehavior.STATE_HIDDEN
                    b.isHideable
                }
                p0.dismiss().apply { p1.toByte() }
                onMapReady(mMap)
            }
            it.create()
            it.show()
        }
    }

    @OnClick(R.id.vFra) fun onOnSite() {
        vFab.visibility = View.GONE
        pBar.visibility = View.VISIBLE
        SharePref(this).save("fab", 1)
        presenter.resOn(this, SharePref(this).getVI("idp")!!)
        vSwh.isChecked = true
        vSwh.setText(R.string.zSOn)
        Handler().postDelayed({ presenter.resPeta(this) }, 5000)
    }

    @OnCheckedChanged(R.id.vSwh) fun onOffline() {
        if (!vSwh.isChecked) {
            SharePref(this).rmValue("fab")
            presenter.resOff(this, SharePref(this).getVI("idp")!!)
            mPet = null
            mMap.clear()
            Utils.visGO(cTol, iMrk, iPfl, iRot, iRep, iDet, 1, 1, 2, 2, 2, 2)
            vSwh.setText(R.string.zSOff)
            mSht.also {
                it.state = BottomSheetBehavior.STATE_HIDDEN
                it.isHideable = true
            }
            vFab.visibility = View.VISIBLE
        } else {
            vSwh.setText(R.string.zSOn)
        }
    }

    @OnClick(R.id.cRpt) fun vReport() {
        if (mPet.isNullOrEmpty()) { txtBar(R.string.zNull) }
        else {
            Utils.visGO(cTol, iMrk, iPfl, iRot, iRep, iDet, 2, 2, 2, 2, 1, 2)
            mSht.also {
                it.state = BottomSheetBehavior.STATE_EXPANDED
            }

            tBar.setNavigationOnClickListener { cClose() }
        }
    }

    @OnClick(R.id.cPfl) fun vProfile() {
        Utils.visGO(cTol, iMrk, iPfl, iRot, iRep, iDet, 2, 2, 1, 2, 2, 2)
        sImg(CRW_URL + SharePref(this).getVS("foto"), cImP)
        tNam.text = SharePref(this).getVS("user")

        if (SharePref(this).getVI("level") == 1) { tLvl.setText(R.string.zAdmin) }
        else if (SharePref(this).getVI("level") == 2){ tLvl.setText(R.string.zPetugas) }

        mSht.also {
            it.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    @OnClick(R.id.cCls) fun cProfile() {
        cClose()
    }

    @OnClick(R.id.cOut) fun eProfile() {
        Utils.visGO(cTol, iMrk, iPfl, iRot, iRep, iDet, 1, 1, 2, 2, 2, 2)
        mSht.also {
            it.state = BottomSheetBehavior.STATE_HIDDEN
            it.isHideable = true
        }
        SharePref(this).clearSP()
        startActivity(Intent(this@CrewView, LoginView::class.java))
        overridePendingTransition(R.anim.slide_down, R.anim.slide_bottom)
    }

    @OnClick(R.id.btn_direc) fun vRoute() {
        cTol.visibility = View.GONE
        mMap.also {
            it.clear()
            it.isTrafficEnabled = false
        }

        mSht.also {
            it.state = BottomSheetBehavior.STATE_HIDDEN
            it.isHideable = true
        }

        presenter.resRot(mLat!!, mLon!!, mPot!!)
    }

    @OnClick(R.id.btn_det) fun vDetail1() {
        for (i in mPet!!.indices) {
            if (mPot!! == LatLng(mPet!![i].lat, mPet!![i].lon)) {
                onDetailListener(mPet as ArrayList<MPeta>, i)
            }
        }
    }

    @OnClick(R.id.iback) fun cRoute() {
        cDir.visibility = View.GONE
        cTol.visibility = View.VISIBLE

        mMap.clear()
        actFus()
        mSht.also {
            it.state = BottomSheetBehavior.STATE_HIDDEN
            it.isHideable = true
        }
    }

    @OnClick(R.id.fMap) fun vPeta() {
        vMarker(mPea!!, mPos!!)
    }

    @OnClick(R.id.cClo) fun cDetail() {
        cClose()
    }

    private fun vMarker(p0: ArrayList<MPeta>, i: Int) {
        Utils.visGO(cTol, iMrk, iPfl, iRot, iRep, iDet, 1, 1, 2, 2, 2, 2)
        mSht.also {
            it.state = BottomSheetBehavior.STATE_COLLAPSED
            it.peekHeight = 475
        }

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(p0[i].lat, p0[i].lon), 16f))
        mPot = LatLng(p0[i].lat, p0[i].lon)

        tJud.text = p0[i].jns
        tAlm.text = p0[i].alm
        sImg(IMG_URL + p0[i].img, vImg)
        actFur()
    }

    private fun txtBar(a: Int) {
        tStt.setText(a)
        tStt.setTypeface(Typeface.DEFAULT, Typeface.ITALIC)
        Handler().postDelayed({
            tStt.setText(R.string.zTitle)
            tStt.setTypeface(Typeface.DEFAULT, Typeface.BOLD)
        }, 3000)
    }

    private fun cClose() {
        Utils.visGO(cTol, iMrk, iPfl, iRot, iRep, iDet, 1, 1, 2, 2, 2, 2)
        mSht.also {
            it.state = BottomSheetBehavior.STATE_HIDDEN
            it.isHideable = true
        }
        actFus()
    }

    private fun sImg(a: String, b: ImageView) {
        val vCPD = CircularProgressDrawable(this).also {
            it.strokeWidth = 5f
            it.centerRadius = 30f
            it.start()
        }

        Glide.with(this)
             .load(a)
             .apply(RequestOptions()
                 .placeholder(vCPD)
                 .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
             .into(b)
    }

    private val mLoB = object: LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            if (p0.locations.size > 0) {
                val mLoc = p0.locations[p0.locations.size - 1]
                if (mMrk != null) mMrk!!.remove()
                mLat = mLoc.latitude
                mLon = mLoc.longitude

                val mP = LatLng(mLat!!, mLon!!)
                MarkerOptions().also {
                    it.position(mP)
                    it.title("Posisi Anda")
                    it.snippet("zero&${Utils.iAlt(this@CrewView, mLat!!, mLon!!)}")
                    it.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))

                    mMrk = mMap.addMarker(it)
                    if (mBld != null) {
                        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(mBld!!.build(), 70))
                    } else {
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mP, 16f))
                    }
                }
            }
        }
    }
}