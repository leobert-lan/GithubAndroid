package osp.leobert.android.github.repo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import osp.leobert.android.github.repo.api.GithubUserApi
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap


/**
 * <p><b>Package:</b> osp.leobert.android.github.repo </p>
 * <p><b>Classname:</b> GHUser </p>
 * Created by leobert on 2020/9/8.
 */

@Entity(tableName = "GHUser")
open class GHUser(

//    @field:ColumnInfo(name =) @field:SerializedName("gists_url")
//    val gistsUrl: String? = null,
//
//    @field:ColumnInfo(name =) @field:SerializedName("repos_url")
//    val reposUrl: String? = null,
//
//    @field:ColumnInfo(name =) @field:SerializedName("following_url")
//    val followingUrl: String? = null,
//
//    @field:ColumnInfo(name =) @field:SerializedName("starred_url")
//    val starredUrl: String? = null,
//
//    @field:ColumnInfo(name =) @field:SerializedName("followers_url")
//    val followersUrl: String? = null,
//
//    @field:ColumnInfo(name =) @field:SerializedName("subscriptions_url")
//    val subscriptionsUrl: String? = null,
//
//    @field:ColumnInfo(name =) @field:SerializedName("received_events_url")
//    val receivedEventsUrl: String? = null,
//    @field:ColumnInfo(name =) @field:SerializedName("events_url")
//    val eventsUrl: String? = null,
    @field:PrimaryKey
    @field:SerializedName("login")
    @field:ColumnInfo(name = "login")
    val login: String,

    @field:SerializedName("twitter_username")
    @field:ColumnInfo(name = "twitter_username")
    val twitterUsername: String? = null,

    @field:SerializedName("bio")
    @field:ColumnInfo(name = "bio")
    val bio: String? = null,

    @field:ColumnInfo(name = "created_at")
    @field:SerializedName("created_at")
    val createdAt: String? = null,


    @field:ColumnInfo(name = "type") @field:SerializedName("type")
    val type: String? = null,

    @field:ColumnInfo(name = "blog") @field:SerializedName("blog")
    val blog: String? = null,

    @field:ColumnInfo(name = "updated_at") @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:ColumnInfo(name = "site_admin") @field:SerializedName("site_admin")
    val siteAdmin: Boolean? = null,

    @field:ColumnInfo(name = "company") @field:SerializedName("company")
    val company: String? = null,

//    @field:ColumnInfo(name =) @field:SerializedName("id")
//    val id: Int? = null,

    @field:ColumnInfo(name = "public_repos") @field:SerializedName("public_repos")
    val publicRepos: Int? = null,

    @field:ColumnInfo(name = "gravatar_id") @field:SerializedName("gravatar_id")
    val gravatarId: String? = null,

    @field:ColumnInfo(name = "email") @field:SerializedName("email")
    val email: String? = null,

    @field:ColumnInfo(name = "organizations_url") @field:SerializedName("organizations_url")
    val organizationsUrl: String? = null,

    @field:ColumnInfo(name = "hireable") @field:SerializedName("hireable")
    val hireable: Boolean? = null,

    @field:ColumnInfo(name = "public_gists") @field:SerializedName("public_gists")
    val publicGists: Int? = null,

    @field:ColumnInfo(name = "url") @field:SerializedName("url")
    val url: String? = null,

    @field:ColumnInfo(name = "html_url") @field:SerializedName("html_url")
    val htmlUrl: String? = null,

    @field:ColumnInfo(name = "followers") @field:SerializedName("followers")
    val followers: Int? = null,

    @field:ColumnInfo(name = "avatar_url") @field:SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @field:ColumnInfo(name = "following") @field:SerializedName("following")
    val following: Int? = null,

    @field:ColumnInfo(name = "name") @field:SerializedName("name")
    val name: String? = null,

    @field:ColumnInfo(name = "location") @field:SerializedName("location")
    val location: String? = null,

    @field:ColumnInfo(name = "node_id") @field:SerializedName("node_id")
    val nodeId: String? = null
) {

    companion object {
        private val users: ConcurrentMap<String, GHUser> = ConcurrentHashMap<String, GHUser>()

        private val api: GithubUserApi by lazy { GithubClient.createApi<GithubUserApi>() }

        suspend fun user(login: String): GHUser? {
            var u = users[login]
            if (u == null) {
                u = api.user(login)
                users[login] = u
            }
            return u
        }

        suspend fun followers(): List<GHUser>? {
            return api.followers()
        }
    }


}