package osp.leobert.android.github.user

import android.content.Context
import osp.leobert.android.github.service.IUserComponentService
import osp.leobert.android.github.user.home.UserHomePageActivity

/**
 * <p><b>Package:</b> osp.leobert.android.github.user </p>
 * <p><b>Project:</b> GithubAndroid </p>
 * <p><b>Classname:</b> ExportService </p>
 * Created by leobert on 2020/9/9.
 */
internal class ExportService : IUserComponentService {
    override fun navigate2Login(context: Context) {

    }

    override fun navigate2UserHomePage(context: Context, login: String) {
        UserHomePageActivity.launch(context, login)
    }
}