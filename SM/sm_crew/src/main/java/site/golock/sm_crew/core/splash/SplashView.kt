package site.golock.sm_crew.core.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import site.golock.sm_crew.R
import site.golock.sm_crew.core.login.LoginView

class SplashView : Activity(), SplashContract.View {

    @BindView(R.id.vIge) lateinit var vIge : ImageView
    @BindView(R.id.tSpl) lateinit var tSpl : TextView

    private lateinit var spPresenter : SplashPresenter

    override lateinit var presenter : SplashContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_splash)
        ButterKnife.bind(this)
        spPresenter = SplashPresenter(this)

        presenter.animSplash(this, tSpl, vIge)
        Handler().postDelayed({
            presenter.cekConnect(this)
        }, 1500)
    }

    override fun actMain() {
        startActivity(Intent(this, LoginView::class.java))
        this.finish()
        overridePendingTransition(R.anim.slide_top, R.anim.slide_bottom)
    }

    override fun actDialog() {
        AlertDialog.Builder(this).also {
            it.setTitle(R.string.zIKoneksi)
            it.setIcon(R.drawable.ic_network)
            it.setMessage(R.string.zMKoneksi)
            it.setPositiveButton(R.string.zOK) {p0, p1->
                this.finish()
                p0.dismiss().apply { p1.toByte() }
            }
            it.create()
            it.show()
        }
    }
}