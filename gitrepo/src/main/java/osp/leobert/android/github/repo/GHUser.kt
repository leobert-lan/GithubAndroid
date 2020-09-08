package osp.leobert.android.github.repo

import com.google.gson.annotations.SerializedName
import osp.leobert.android.github.repo.api.GithubUserApi
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap


/**
 * <p><b>Package:</b> osp.leobert.android.github.repo </p>
 * <p><b>Classname:</b> GHUser </p>
 * Created by leobert on 2020/9/8.
 */

class GHUser(

    @field:SerializedName("gists_url")
    val gistsUrl: String? = null,

    @field:SerializedName("repos_url")
    val reposUrl: String? = null,

    @field:SerializedName("following_url")
    val followingUrl: String? = null,

    @field:SerializedName("twitter_username")
    val twitterUsername: String? = null,

    @field:SerializedName("bio")
    val bio: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("login")
    val login: String? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("blog")
    val blog: String? = null,

    @field:SerializedName("subscriptions_url")
    val subscriptionsUrl: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("site_admin")
    val siteAdmin: Boolean? = null,

    @field:SerializedName("company")
    val company: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("public_repos")
    val publicRepos: Int? = null,

    @field:SerializedName("gravatar_id")
    val gravatarId: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("organizations_url")
    val organizationsUrl: String? = null,

    @field:SerializedName("hireable")
    val hireable: String? = null,

    @field:SerializedName("starred_url")
    val starredUrl: String? = null,

    @field:SerializedName("followers_url")
    val followersUrl: String? = null,

    @field:SerializedName("public_gists")
    val publicGists: Int? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("received_events_url")
    val receivedEventsUrl: String? = null,

    @field:SerializedName("followers")
    val followers: Int? = null,

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @field:SerializedName("events_url")
    val eventsUrl: String? = null,

    @field:SerializedName("html_url")
    val htmlUrl: String? = null,

    @field:SerializedName("following")
    val following: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("location")
    val location: String? = null,

    @field:SerializedName("node_id")
    val nodeId: String? = null
) {

    companion object {
        private val users: ConcurrentMap<String, GHUser> = ConcurrentHashMap<String, GHUser>()

        private val api: GithubUserApi by lazy { GithubClient.createApi<GithubUserApi>() }

        suspend fun user(login: String): GHUser {
            var u = users[login]
            if (u == null) {
                u = api.user(login)
                users[login] = u
            }
            return u
        }
    }


}