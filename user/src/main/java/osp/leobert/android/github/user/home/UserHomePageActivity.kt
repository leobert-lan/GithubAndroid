package osp.leobert.android.github.user.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import osp.leobert.android.github.base.BaseActivity
import osp.leobert.android.github.user.R
import osp.leobert.android.github.user.databinding.UserActivityUserHomePageBinding

class UserHomePageActivity : BaseActivity<UserActivityUserHomePageBinding>() {

    companion object {
        const val ext_str_login = "ext_str_login"
        fun launch(context: Context,login:String) {
            Intent(context,UserHomePageActivity::class.java)
                .putExtra(ext_str_login,login)
                .let {
                    context.startActivity(it)
                }
        }
    }


    override val layoutId: Int
        get() = R.layout.user_activity_user_home_page

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewBinding?.fgContainer?.let {
//            supportFragmentManager.beginTransaction()
//            supportFragmentManager.
//        }
    }
}