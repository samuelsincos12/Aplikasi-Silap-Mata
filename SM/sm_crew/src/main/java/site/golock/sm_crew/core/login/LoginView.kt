package site.golock.sm_crew.core.login

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import site.golock.sm_crew.R
import site.golock.sm_crew.core.crew.CrewView
import site.golock.sm_crew.util.SharePref

class LoginView: Activity(), LoginContract.View {

    @BindView(R.id.tUsr) lateinit var eUsr : EditText
    @BindView(R.id.tPaw) lateinit var ePaw : EditText
    @BindView(R.id.txSM) lateinit var txSM : TextView

    private lateinit var logPres: LoginPresenter

    private var mBPS: Boolean = false

    override lateinit var presenter: LoginContract.Presenter

    override fun onStart() {
        super.onStart()
        cekStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_login)
        ButterKnife.bind(this)
        logPres = LoginPresenter(this)

        txSM.typeface = Typeface.createFromAsset(assets, "richardm.ttf")
    }

    override fun onBackPressed() {
        if (mBPS) super.onBackPressed()
        mBPS = true
        Toast.makeText(this, R.string.zBack, Toast.LENGTH_LONG).show()
        Handler().postDelayed({ mBPS = false }, 2000)
    }

    override fun cekStart() {
        if (SharePref(this).inLog("user")) {
            actRes()
        }
    }

    override fun sPref(a: Int, b: String, c: String, d: Int) {
        SharePref(this).save("id", a)
        SharePref(this).save("user", b)
        SharePref(this).save("foto", c)
        SharePref(this).save("level", d)
    }

    override fun showToast(a: String) {
        Toast.makeText(this, a, Toast.LENGTH_LONG).show()
    }

    override fun actRes() {
        startActivity(Intent(this@LoginView, CrewView::class.java))
        this.finish()
        overridePendingTransition(R.anim.slide_top, R.anim.slide_bottom)
    }

    @OnClick(R.id.bLog) fun onLogin() {
        val a = eUsr.text.toString().trim()
        val b = ePaw.text.toString().trim()
        presenter.resLogin(this, a, b)
    }
}