package osp.leobert.android.github.repo.api

import okhttp3.Interceptor
import okhttp3.Response

/**
 * <p><b>Package:</b> osp.leobert.android.github.repo.api </p>
 * <p><b>Classname:</b> AuthInterceptor </p>
 * Created by leobert on 2020/9/9.
 */
class AuthInterceptor(private val provider: () -> String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        return chain.proceed(
            originalRequest.newBuilder()
                .addHeader("Authorization", "token ${provider.invoke()}")
                .build()
        )
    }
}