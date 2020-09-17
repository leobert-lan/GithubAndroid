package osp.leobert.android.github.repo

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import osp.leobert.android.github.repo.api.HeaderAcceptInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * <p><b>Package:</b> osp.leobert.android.github.repo </p>
 * <p><b>Classname:</b> GithubClient </p>
 * Created by leobert on 2020/9/8.
 */
object GithubClient {
    var okHttpClient =
        OkHttpClient().newBuilder()
            .readTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .apply {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                addInterceptor(loggingInterceptor)
                addInterceptor(HeaderAcceptInterceptor())
            }

    var client: Retrofit = create()

    private fun create(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .client(okHttpClient.build())
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder()
                    .setPrettyPrinting()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .serializeNulls().create()
            )
        )
        .build()

    fun reCreate() {
        client = create()
    }

//        httpsBuilder.addInterceptor(OverrideTimeoutInterceptor())


    inline fun <reified T> createApi(): T = client.create(T::class.java)
}