package site.golock.sm_crew.core.login

import android.content.Context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import site.golock.sm_crew.model.RLog
import site.golock.sm_crew.network.NetClient
import site.golock.sm_crew.util.Constant.BASE_URL

class LoginPresenter(val logView: LoginContract.View): LoginContract.Presenter {

    init {
        logView.presenter = this
    }

    override fun resLogin(ctx: Context, a: String, b: String) {
        NetClient(ctx, BASE_URL).getClient().mLog(a, b).enqueue(object: Callback<RLog> {
            override fun onResponse(call: Call<RLog>, response: Response<RLog>) {
                if (!response.body()!!.error) {
                    logView.sPref(
                        response.body()!!.driver.idp,
                        response.body()!!.driver.nama,
                        response.body()!!.driver.photo,
                        response.body()!!.driver.level
                    )
                    logView.actRes()
                } else {
                    logView.showToast(response.body()!!.message)
                }
            }

            override fun onFailure(call: Call<RLog>, t: Throwable) {
                logView.showToast("error")
            }
        })
    }

    override fun start() { TODO("not implemented") }
}