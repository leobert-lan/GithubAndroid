package osp.leobert.android.github.repo.api

import osp.leobert.android.github.repo.GHUser
import retrofit2.http.*

/**
 * <p><b>Package:</b> osp.leobert.android.github.repo.api </p>
 * <p><b>Classname:</b> GithubUserApi </p>
 * Created by leobert on 2020/9/8.
 */
interface GithubUserApi {

//    @Headers("Accept: application/vnd.github.v3+json") //拦截器处理

    @GET("users/{user}")
    suspend fun user(@Path("user") user: String): GHUser

    @GET("user")
    suspend fun authUser(@HeaderMap token:Map<String,String>): GHUser

    @GET("user/followers")
    suspend fun followers():List<GHUser>

    /**
     * ```
     * accept 	string 	header
     * Setting to application/vnd.github.v3+json is recommended
     * username 	string 	path
     * subject_type 	string 	query
     *
     * Identifies which additional information you'd like to receive about the person's hovercard.
     * Can be organization, repository, issue, pull_request. Required when using subject_id.
     *
     * subject_id 	string 	query
     *
     * Uses the ID for the subject_type you specified. Required when using subject_type.
     *
     * {
    "contexts": [
    {
    "message": "Owns this repository",
    "octicon": "repo"
    }
    ]
    }
     * ```
     * */
    @GET("users/{user}/hovercard")
    //todo response
    suspend fun userHovercard(@Path("user") user: String): GHUser

    @PUT("user/blocks/{user}")
    suspend fun blockUser(@Path("user") user: String): Any?

    @DELETE("user/blocks/{user}")
    suspend fun unBlockUser(@Path("user") user: String): Any?

}