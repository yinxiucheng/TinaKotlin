package com.tina.base.data.net

import com.tina.base.common.BaseConstant
import com.tina.base.utils.AppPrefsUtils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author yxc
 * @date 2018/9/15
 */
class RetrofitFactory private constructor() {

    companion object {
        val instance: RetrofitFactory by lazy { RetrofitFactory() }
    }

    private val retrofit: Retrofit
    private val interceptor: Interceptor

    init {
        interceptor = Interceptor { chain ->
            val request = chain.request()
                    .newBuilder()
                    .addHeader("Content_Type","application/json")
                    .addHeader("charset","UTF-8")
                    .addHeader("token", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
                    .build()
            chain.proceed(request)
        }
        retrofit = Retrofit.Builder()
                .baseUrl(BaseConstant.SERVER_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(initOkHttpClient())
                .build()
    }

    private fun initOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(initLogInterceptor())
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build()

    }

    private fun initLogInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }

}