package osp.leobert.android.github.user.login

/**
 * <p><b>Package:</b> osp.leobert.android.github.user.login </p>
 * <p><b>Project:</b> GithubAndroid </p>
 * <p><b>Classname:</b> Contract </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2020/9/10.
 */
class Contract {
    interface IView {

    }

    interface IViewModel {
        fun checkToken()
    }
}