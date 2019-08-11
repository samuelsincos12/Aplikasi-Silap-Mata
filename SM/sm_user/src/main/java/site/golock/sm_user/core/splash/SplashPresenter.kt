package site.golock.sm_user.core.splash

import android.content.Context
import android.graphics.Typeface
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import site.golock.sm_user.R
import site.golock.sm_user.util.Utils

class SplashPresenter(private val spView: SplashContract.View): SplashContract.Presenter {

    init {
        spView.presenter = this
    }

    override fun cekConnect(ctx: Context) {
        if (!Utils.checkConnect(ctx)) {
            spView.actDialog()
        } else {
            spView.actMain()
        }
    }

    override fun animSplash(ctx: Context, a: TextView, b: ImageView) {
        a.typeface = Typeface.createFromAsset(ctx.assets, "richardm.ttf")

        AnimationUtils.loadAnimation(ctx, R.anim.simple_grow).also {
            it.duration = 800
            a.startAnimation(it)
        }

        AnimationUtils.loadAnimation(ctx, R.anim.simple_grow).also {
            it.duration = 500
            b.startAnimation(it)
        }
    }

    override fun start() { TODO("not implemented") }
}