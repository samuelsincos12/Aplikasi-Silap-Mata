package site.golock.sm_crew.core.login

import android.content.Context
import site.golock.sm_crew.core.BaseContract

interface LoginContract {

    interface View: BaseContract.BaseView<Presenter> {
        fun cekStart()
        fun sPref(a: Int, b: String, c: String, d: Int)
        fun showToast(a: String)
        fun actRes()
    }

    interface Presenter: BaseContract.BasePresenter {
        fun resLogin(ctx: Context, a: String, b: String)
    }
}