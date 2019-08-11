package site.golock.sm_user.core.reg

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.view.Window
import android.widget.Toast
import butterknife.ButterKnife
import butterknife.OnClick
import site.golock.sm_user.R
import site.golock.sm_user.core.user.UserView

class RegActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_reg)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.bDaf) fun onRegs() {
        Handler().postDelayed({
            Toast.makeText(this, "Registrasi berhasil, silakan tunggu sms balasan untuk verifikasi no.hp", Toast.LENGTH_LONG).show()
        }, 2000)
        Handler().postDelayed({
            AlertDialog.Builder(this).also {
                it.setTitle("Masukkan Kode")
                it.setView(R.layout.dbox)
                it.setPositiveButton("OK") { p0, p1 ->
                    startActivity(Intent(this, UserView::class.java))
                    this.finish()
                    overridePendingTransition(R.anim.slide_top, R.anim.slide_bottom)
                    p0.dismiss().apply { p1.toByte() }
                }
                it.create()
                it.show()
            }
        }, 3000)
    }
}
