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

    //lists the people who the authenticated user follows.
    @GET("user/following")
    suspend fun followings():List<GHUser>

    //Check if a person is followed by the authenticated user
    @GET("user/following/{user}")
    suspend fun isFollowing(@Path("user") user: String):Any?

    @PUT("user/following/{user}")
    suspend fun followUser(@Path("user") user: String):Any?

    @DELETE("user/following/{user}")
    suspend fun unFollowUser(@Path("user") user: String):Any?

    @GET("user/{user}/followers")
    suspend fun followersOfUser(@Path("user") user:String):List<GHUser>

    /**
     * todo
     * per_page 	integer 	query Results per page (max 100)
    page 	integer 	query Page number of the results to fetch.
     * */
    @GET("user/{user}/following")
    suspend fun followingsOfUser(@Path("user") user:String):List<GHUser>

    // 暂时用不着的功能
//    Check if a user follows another user
//
//    get /users/{username}/following/{target_user}
//
//    Parameters
//    Name 	Type 	In 	Description
//    accept 	string 	header
//
//    Setting to application/vnd.github.v3+json is recommended
//    username 	string 	path
//    target_user 	string 	path


//    @GET("users/{user}/hovercard")
//    //todo response
//    suspend fun userHovercard(@Path("user") user: String): GHUser

    @PUT("user/blocks/{user}")
    suspend fun blockUser(@Path("user") user: String): Any?

    @DELETE("user/blocks/{user}")
    suspend fun unBlockUser(@Path("user") user: String): Any?

}