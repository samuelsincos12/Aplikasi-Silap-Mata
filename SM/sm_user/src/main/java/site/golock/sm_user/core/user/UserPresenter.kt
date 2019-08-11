package site.golock.sm_user.core.user

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import site.golock.sm_user.BuildConfig
import site.golock.sm_user.model.SReport
import site.golock.sm_user.network.NetClient
import site.golock.sm_user.util.Constant.BASE_URL
import site.golock.sm_user.util.Constant.ReqCamCode
import site.golock.sm_user.util.Constant.ReqPermsCode
import java.io.File

class UserPresenter(val userView: UserContract.View): UserContract.Presenter {

    init {
        userView.presenter = this
    }

    override lateinit var uPic: Uri

    override fun cekPermission(ctx: Context): Boolean {
        val pc = ContextCompat.checkSelfPermission(ctx, Manifest.permission_group.CAMERA)
        val pl = ContextCompat.checkSelfPermission(ctx, Manifest.permission_group.LOCATION)
        val ps = ContextCompat.checkSelfPermission(ctx, Manifest.permission_group.STORAGE)
        return pc == PackageManager.PERMISSION_GRANTED && pl == PackageManager.PERMISSION_GRANTED &&
               ps == PackageManager.PERMISSION_GRANTED
    }

    override fun reqPermission(rc: Int, gr: IntArray) {
        if (rc == ReqPermsCode && gr.isNotEmpty()) {
            val bc = gr[0] == PackageManager.PERMISSION_GRANTED
            val bl = gr[1] == PackageManager.PERMISSION_GRANTED
            val bs = gr[2] == PackageManager.PERMISSION_GRANTED
            if (bc && bl && bs) {
                userView.cekGPS()
            } else {
                userView.actReqFal()
            }
        }
    }

    override fun resFile(): File {
        File("${Environment.getExternalStorageDirectory()}/SilapMata").also { dr-> dr.mkdir()
            val fl= File(dr, "sm_${(0..20000).random()}.jpg")
            return fl.apply { uPic = Uri.fromFile(fl) }
        }
    }

    override fun reqCamera(ctx: Context) {
        val nt = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val um = FileProvider.getUriForFile(ctx, "${BuildConfig.APPLICATION_ID}.provider", resFile())
        nt.putExtra(MediaStore.EXTRA_OUTPUT, um)
        startActivityForResult(ctx as Activity, nt, ReqCamCode, null)
    }

    override fun resLap(ctx: Context, a: File, b: String, c: String, d: String, e: String, f: String) {
        val n = RequestBody.create(MediaType.parse("image/*"), a)
        val n0 = MultipartBody.Part.createFormData("img", a.name, n)
        val n1 = RequestBody.create(MediaType.parse("text/plain"), b)
        val n2 = RequestBody.create(MediaType.parse("text/plain"), c)
        val n3 = RequestBody.create(MediaType.parse("text/plain"), d)
        val n4 = RequestBody.create(MediaType.parse("text/plain"), e)
        val n5 = RequestBody.create(MediaType.parse("text/plain"), f)

        NetClient(ctx, BASE_URL).getClient().mRep(n0, n1, n2, n3, n4, n5).enqueue(object: Callback<SReport> {
            override fun onResponse(call: Call<SReport>, response: Response<SReport>) {
                if (!response.body()!!.error) {
                    userView.actRes(response.body()!!.message)
                } else {
                    userView.actRes(response.body()!!.message)
                }
                call.cancel()
            }

            override fun onFailure(call: Call<SReport>, t: Throwable) {
                userView.actFail()
                call.cancel()
            }
        })
    }

    override fun start() { TODO("not implemented") }
}