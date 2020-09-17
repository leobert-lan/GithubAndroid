package osp.leobert.android.github.user

import android.content.Context
import android.util.Log
import net.grandcentrix.tray.TrayPreferences
import osp.leobert.android.github.service.IUserComponentService
import osp.leobert.android.github.user.home.UserHomePageActivity
import osp.leobert.android.github.user.login.LoginActivity

/**
 * <p><b>Package:</b> osp.leobert.android.github.user </p>
 * <p><b>Project:</b> GithubAndroid </p>
 * <p><b>Classname:</b> ExportService </p>
 * Created by leobert on 2020/9/9.
 */
internal class ExportService : IUserComponentService {
    override var trayPreference: TrayPreferences? = null

    override fun navigate2Login(context: Context) {
        LoginActivity.launch(context)
    }

    override fun navigate2UserHomePage(context: Context, login: String) {
        UserHomePageActivity.launch(context, login)
    }

    override fun lastLogin(): String? {
        Log.e("lmsg","${trayPreference == null}")
        return trayPreference?.getString("last_login", null)
    }

    override fun saveLastLogin(login: String) {
        trayPreference?.put("last_login", login)
    }
}