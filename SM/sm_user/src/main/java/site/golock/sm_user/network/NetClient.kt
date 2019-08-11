package site.golock.sm_user.network

import android.content.Context
import android.util.Log
import okhttp3.Cache
import okhttp3.CacheControl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import site.golock.sm_user.util.Utils
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

class NetClient(private val ctx: Context, private val a: String) {

    private var retrofit : Retrofit? = null

    private fun provideCache(): Cache {
        var cache: Cache? = null
        try {
            cache = Cache(File(ctx.cacheDir, "http-cache"), 10 * 1024 * 1024)
        } catch (e: IOException) {
            Log.e("ERROR" , e.toString())
        }
        return cache!!
    }

    private fun setClient(): Retrofit? {
        if (retrofit == null) {
            val interceptor = HttpLoggingInterceptor().also {
                it.level = HttpLoggingInterceptor.Level.BODY
            }

            val httpClient = OkHttpClient.Builder().also {
                it.connectTimeout(30, TimeUnit.SECONDS)
                it.readTimeout(30, TimeUnit.SECONDS)
                it.cache(provideCache())
                it.addInterceptor(interceptor)
                it.addNetworkInterceptor { chain ->
                    val res = chain.proceed(chain.request())
                    val ctr = if (Utils.checkConnect(ctx)) {
                        CacheControl.Builder().maxAge(0, TimeUnit.SECONDS).build()
                    } else {
                        CacheControl.Builder().maxStale(3, TimeUnit.DAYS).build()
                    }
                    res.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", ctr.toString())
                        .build()
                }
                it.addInterceptor { chain->
                    var req = chain.request()
                    if (!Utils.checkConnect(ctx)) {
                        val ctr = CacheControl.Builder().maxStale(3, TimeUnit.DAYS).build()
                        req = req.newBuilder()
                            .removeHeader("Pragma")
                            .removeHeader("Cache-Control")
                            .cacheControl(ctr)
                            .build()
                    }
                    chain.proceed(req)
                }
            }

            retrofit = Retrofit.Builder()
                               .baseUrl(a)
                               .addConverterFactory(GsonConverterFactory.create())
                               .client(httpClient.build())
                               .build()
        }
        return retrofit
    }

    fun getClient(): NetServices { return setClient()!!.create(NetServices::class.java) }
}