package site.golock.sm_crew.util

import android.content.Context

class SharePref(private val ctx: Context) {

    private val PREF_NAME: String = "smpref"
    private val sp = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun save(KEY_NAME: String, txt: String) {
        sp.edit().also {
            it.putString(KEY_NAME, txt)
            it!!.apply()
        }
    }

    fun save(KEY_NAME: String, i: Int) {
        sp.edit().also {
            it.putInt(KEY_NAME, i)
            it!!.apply()
        }
    }

    fun save(KEY_NAME: String, b: Boolean) {
        sp.edit().also {
            it.putBoolean(KEY_NAME, b)
            it!!.apply()
        }
    }

    fun getVS(KEY_NAME: String): String? {
        return sp.getString(KEY_NAME, null)
    }

    fun getVI(KEY_NAME: String): Int? {
        return sp.getInt(KEY_NAME, 0)
    }

    fun getVB(KEY_NAME: String, df: Boolean): Boolean? {
        return  sp.getBoolean(KEY_NAME, df)
    }

    fun inLog(KEY_NAME: String): Boolean {
        if (sp.getString(KEY_NAME, null) != null) {
            return true
        }
        return false
    }

    fun clearSP() {
        sp.edit().also {
            it.clear()
            it!!.apply()
        }
    }

    fun rmValue(KEY_NAME: String) {
        sp.edit().also {
            it.remove(KEY_NAME)
            it!!.apply()
        }
    }
}