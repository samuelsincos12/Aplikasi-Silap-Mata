package site.golock.sm_crew.core.crew

import android.content.Context
import com.directions.route.Route
import com.google.android.gms.maps.model.LatLng
import site.golock.sm_crew.model.MPeta
import site.golock.sm_crew.core.BaseContract

interface CrewContract {

    interface View: BaseContract.BaseView<Presenter> {
        fun cekStart()
        fun actReqFal()
        fun cekGPS()
        fun actOnOff(a: String)
        fun actRes(p0: List<MPeta>?)
        fun actFail()
        fun actRot(p0: ArrayList<Route>)
        fun actFus()
        fun actFur()
    }

    interface Presenter: BaseContract.BasePresenter {
        fun cekPermission(ctx: Context): Boolean
        fun reqPermission(rc: Int, gr: IntArray)
        fun resPeta(ctx: Context)
        fun resOn(ctx: Context, i: Int)
        fun resOff(ctx: Context, i: Int)
        fun resRot(a: Double, b: Double, c: LatLng)
        fun resVer(ctx: Context, a: Int, b: Int, c: Int)
    }
}