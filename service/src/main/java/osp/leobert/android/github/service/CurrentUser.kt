package osp.leobert.android.github.service

import androidx.lifecycle.MutableLiveData
import osp.leobert.android.github.repo.*
import osp.leobert.android.github.repo.api.AuthInterceptor

/**
 * <p><b>Package:</b> osp.leobert.android.github.service </p>
 * <p><b>Classname:</b> CurrentUser </p>
 * Created by leobert on 2020/9/21.
 */
object CurrentUser {
    val user: MutableLiveData<GHUser?> = MutableLiveData(null)
    private var token: String = ""

    init {
        GithubClient.okHttpClient.addInterceptor(AuthInterceptor { token })
        GithubClient.reCreate()
    }

    fun loadByToken(token: String) {
        this.token = token

        repo(
            request = { GHUser.userByToken("token $token") },
            repoRead = {
                RepoDatabase.db?.userDao()?.findUserByToken(token)
            },
            repoUpdate = {
                it?.let { user ->
                    RepoDatabase.db?.userDao()?.insertOrUpdate(user)
                }
            },
            onSuccess = {
                user.postValue(it)
            },
            onFailure = {
                user.postValue(null)
            }
        )
    }
}