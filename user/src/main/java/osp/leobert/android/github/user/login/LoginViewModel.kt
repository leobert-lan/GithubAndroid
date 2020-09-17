package osp.leobert.android.github.user.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import osp.leobert.android.github.repo.*
import osp.leobert.android.github.service.IUserComponentService
import osp.leobert.android.github.service.magnetRun

/**
 * <p><b>Package:</b> osp.leobert.android.github.user.login </p>
 * <p><b>Project:</b> GithubAndroid </p>
 * <p><b>Classname:</b> LoginViewModel </p>
 * Created by leobert on 2020/9/9.
 */
class LoginViewModel : ViewModel(), Contract.IViewModel {
    var login: String? = null
    var token: String? = null

    val currentUser: MutableLiveData<GHUser> = MutableLiveData()

    override fun checkToken() {
        val l = login ?: return
        val t = token ?: return

        repo(request = { GHUser.userByToken("token $token") },
            onSuccess = {
                magnetRun<IUserComponentService> { it.saveLastLogin(l) }
            }, onFailure = {
                Log.e("LoginViewModel", "err", it)
            }, repoRead = { null },
            repoUpdate = {
                RepoDatabase.db?.tokenDao()?.saveOrUpdate(
                    GHLogin(l, t)
                )
            })
    }
}