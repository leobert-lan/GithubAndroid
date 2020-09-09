package osp.leobert.android.github.user

import osp.leobert.android.github.service.IUserComponentService
import osp.leobert.magnet.Magnet
import osp.leobert.magnet.com.ComponentObjectModel

/**
 * <p><b>Package:</b> osp.leobert.android.github.user </p>
 * <p><b>Project:</b> GithubAndroid </p>
 * <p><b>Classname:</b> UserComponent </p>
 * Created by leobert on 2020/9/9.
 */
class UserComponent : ComponentObjectModel {
    override fun onCreate() {
        Magnet.getInstance().addService(IUserComponentService::class.java,ExportService())
    }

    override fun onDestroy() {
    }
}