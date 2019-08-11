package site.golock.sm_crew.core.splash

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import site.golock.sm_crew.core.BaseContract

interface SplashContract {

    interface View: BaseContract.BaseView<Presenter> {
        fun actMain()
        fun actDialog()
    }

    interface Presenter: BaseContract.BasePresenter {
        fun cekConnect(ctx: Context)
        fun animSplash(ctx: Context, a: TextView, b: ImageView)
    }
}