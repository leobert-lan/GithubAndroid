package osp.leobert.android.github

import android.app.Application
import androidx.room.Room
import net.grandcentrix.tray.AppPreferences
import osp.leobert.android.github.repo.GithubClient
import osp.leobert.android.github.repo.RepoDatabase
import osp.leobert.android.github.repo.api.AuthInterceptor
import osp.leobert.android.github.repo.db

/**
 * <p><b>Package:</b> osp.leobert.android.github </p>
 * <p><b>Classname:</b> GithubApp </p>
 * Created by leobert on 2020/9/9.
 */
class GithubApp : Application() {

    var token: String = ""


    override fun onCreate() {
        super.onCreate()
        GithubClient.okHttpClient.addInterceptor(AuthInterceptor { token })
        RepoDatabase.db =
            Room.databaseBuilder<RepoDatabase>(this, RepoDatabase::class.java, "repo-database")
                .build()

        db(curd = {
            AppPreferences(this).getString("last_login", null)?.let { lastLogin ->
                RepoDatabase.db?.tokenDao()?.findToken(lastLogin)?.token
            }
        }, onSuccess = {
            this@GithubApp.token = it ?: ""
        }, onFailure = {})


    }
}