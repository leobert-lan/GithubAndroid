package osp.leobert.android.github.repo.api

import osp.leobert.android.github.repo.GHUser
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * <p><b>Package:</b> osp.leobert.android.github.repo.api </p>
 * <p><b>Classname:</b> GithubUserApi </p>
 * Created by leobert on 2020/9/8.
 */
interface GithubUserApi {

    @GET("users/{user}")
    suspend fun user(@Path("user") user: String): GHUser
}