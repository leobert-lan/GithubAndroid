package osp.leobert.android.github.user.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import osp.leobert.android.github.repo.*

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

        repo(request = { GHUser.userByToken() },
            onSuccess = {
                db(curd = {

                }, onSuccess = {}, onFailure = {})
            }, onFailure = {

            }, repoRead = { null },
            repoUpdate = {
                RepoDatabase.db?.tokenDao()?.saveOrUpdate(
                    GHLogin(l, t)
                )
            })
    }
}