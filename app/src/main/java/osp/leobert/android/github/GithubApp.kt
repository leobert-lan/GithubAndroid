package osp.leobert.android.github

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.room.Room
import net.grandcentrix.tray.AppPreferences
import osp.leobert.android.github.repo.GHUser
import osp.leobert.android.github.repo.GithubClient
import osp.leobert.android.github.repo.RepoDatabase
import osp.leobert.android.github.repo.api.AuthInterceptor
import osp.leobert.android.github.repo.db
import osp.leobert.android.github.service.CurrentUser
import osp.leobert.android.github.service.IUserComponentService
import osp.leobert.android.github.service.magnetRun
import osp.leobert.android.github.service.magnetRun2
import osp.leobert.android.pandora.Logger
import osp.leobert.android.pandora.Pandora

/**
 * <p><b>Package:</b> osp.leobert.android.github </p>
 * <p><b>Classname:</b> GithubApp </p>
 * Created by leobert on 2020/9/9.
 */
class GithubApp : Application() {

//    var token: String = ""


    override fun onCreate() {
        super.onCreate()
        Logger.DEBUG = true

        //目前注册是在onCreate的最后，所以异步处理一下
        Handler(Looper.getMainLooper()).post {

            RepoDatabase.db =
                Room.databaseBuilder<RepoDatabase>(this, RepoDatabase::class.java, "repo-database")
                    .build()
            magnetRun<IUserComponentService> {
                it.trayPreference = AppPreferences(this)
                CurrentUser.user.postValue(GHUser(it.lastLogin() ?: ""))
            }

            db(curd = {
                magnetRun2<IUserComponentService, String>({
                    it.lastLogin()?.let { lastLogin ->
                        RepoDatabase.db?.tokenDao()?.findToken(lastLogin)?.token
                    } ?: ""
                }, "")
            }, onSuccess = {
                CurrentUser.loadByToken(it)
            }, onFailure = {
                Log.e("GithubApp", "token failure", it)
            })
        }

    }
}