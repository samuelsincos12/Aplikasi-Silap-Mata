package site.golock.sm_crew.core.crew

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import android.util.Log
import com.directions.route.*
import com.google.android.gms.maps.model.LatLng
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import site.golock.sm_crew.model.RLog
import site.golock.sm_crew.model.RPeta
import site.golock.sm_crew.model.RVers
import site.golock.sm_crew.network.NetClient
import site.golock.sm_crew.util.Constant.BASE_URL
import site.golock.sm_crew.util.Constant.KEY_ROUTE
import site.golock.sm_crew.util.Constant.ReqPermsCode

class CrewPresenter(val crewView: CrewContract.View): CrewContract.Presenter {

    init {
        crewView.presenter = this
    }

    override fun cekPermission(ctx: Context): Boolean {
        val p1 = ContextCompat.checkSelfPermission(ctx, Manifest.permission_group.LOCATION)
        return p1 == PackageManager.PERMISSION_GRANTED
    }

    override fun reqPermission(rc: Int, gr: IntArray) {
        if (rc == ReqPermsCode && gr.isNotEmpty()) {
            val bl = gr[0] == PackageManager.PERMISSION_GRANTED
            if (bl) {
                crewView.cekGPS()
            } else {
                crewView.actReqFal()
            }
        }
    }

    override fun resPeta(ctx: Context) {
        NetClient(ctx, BASE_URL).getClient().mPeta().enqueue(object: Callback<RPeta> {
            override fun onResponse(call: Call<RPeta>, response: Response<RPeta>) {
                if (!response.body()!!.error) {
                    crewView.actRes(response.body()!!.data)
                }
                call.cancel()
            }

            override fun onFailure(call: Call<RPeta>, t: Throwable) {
                Log.d("LAYANAN", "Pesan", t)
                crewView.actFail()
                call.cancel()
            }
        })
    }

    override fun resRot(a: Double, b: Double, c: LatLng) {
        Routing.Builder()
            .key(KEY_ROUTE)
            .waypoints(LatLng(a, b), c)
            .travelMode(AbstractRouting.TravelMode.DRIVING)
            .avoid(AbstractRouting.AvoidKind.HIGHWAYS)
            .alternativeRoutes(true)
            .language("ID")
            .withListener(object: RoutingListener {
                override fun onRoutingSuccess(p0: ArrayList<Route>?, p1: Int) {
                    crewView.actFur()
                    crewView.actRot(p0!!)
                }

                override fun onRoutingFailure(p0: RouteException?) { Log.d("RUTE", "Pesan", p0!!) }
                override fun onRoutingStart() { }
                override fun onRoutingCancelled() { }
            })
            .build().also {
                it.execute()
            }
    }

    override fun resOn(ctx: Context, i: Int) {
        NetClient(ctx, BASE_URL).getClient().mOn(i).enqueue(object: Callback<RLog> {
            override fun onResponse(call: Call<RLog>, response: Response<RLog>) {
                if (!response.body()!!.error) {
                    crewView.actOnOff(response.body()!!.message)
                } else {
                    crewView.actOnOff(response.body()!!.message)
                }
            }

            override fun onFailure(call: Call<RLog>, t: Throwable) {
                Log.e("ONLINE", "Status", t)
                crewView.actFail()
                call.cancel()
            }
        })
    }

    override fun resOff(ctx: Context, i: Int) {
        NetClient(ctx, BASE_URL).getClient().mOff(i).enqueue(object: Callback<RLog> {
            override fun onResponse(call: Call<RLog>, response: Response<RLog>) {
                if (!response.body()!!.error) {
                    crewView.actOnOff(response.body()!!.message)
                } else {
                    crewView.actOnOff(response.body()!!.message)
                }
            }

            override fun onFailure(call: Call<RLog>, t: Throwable) {
                Log.e("ONLINE", "Status", t)
                crewView.actFail()
                call.cancel()
            }
        })
    }

    override fun resVer(ctx: Context, a: Int, b: Int, c: Int) {
        NetClient(ctx, BASE_URL).getClient().mVer(a, b, c).enqueue(object: Callback<RVers> {
            override fun onResponse(call: Call<RVers>, response: Response<RVers>) {
                if (!response.body()!!.error) {
                    crewView.actOnOff(response.body()!!.message) }
                else {
                    crewView.actOnOff(response.body()!!.message) }
            }

            override fun onFailure(call: Call<RVers>, t: Throwable) {
                Log.e("VALID", "Status", t)
                crewView.actFail()
                call.cancel()
            }
        })
    }

    override fun start() { TODO("not implemented") }
}