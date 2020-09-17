package osp.leobert.android.github.service

import android.content.Context
import net.grandcentrix.tray.TrayPreferences
import osp.leobert.magnet.com.IComService

/**
 * <p><b>Package:</b> osp.leobert.android.github.service </p>
 * <p><b>Project:</b> GithubAndroid </p>
 * <p><b>Classname:</b> IUserComponentService </p>
 * Created by leobert on 2020/9/9.
 */
interface IUserComponentService : IComService {

    var trayPreference: TrayPreferences?

    fun navigate2Login(context: Context)

    fun navigate2UserHomePage(context: Context, login: String)

    fun lastLogin():String?

    fun saveLastLogin(login:String)


}