package site.golock.sm_user.core.user

import android.content.Context
import android.net.Uri
import site.golock.sm_user.core.BaseContract
import java.io.File

interface UserContract {

    interface View: BaseContract.BaseView<Presenter> {
        fun actReqFal()
        fun cekGPS()
        fun actFus()
        fun actRes(a: String)
        fun actFail()
    }

    interface Presenter: BaseContract.BasePresenter {
        var uPic: Uri
        fun cekPermission(ctx: Context): Boolean
        fun reqPermission(rc: Int, gr: IntArray)
        fun resFile(): File
        fun reqCamera(ctx: Context)
        fun resLap(ctx: Context, a: File, b: String, c: String, d: String, e: String, f: String)
    }
}