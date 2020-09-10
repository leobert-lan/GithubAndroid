package osp.leobert.android.github.user.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import osp.leobert.android.github.base.BaseActivity
import osp.leobert.android.github.repo.GHUser
import osp.leobert.android.github.user.R
import osp.leobert.android.github.user.databinding.UserActivityLoginBinding

class LoginActivity : BaseActivity<UserActivityLoginBinding>(), Contract.IView {
    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewBinding?.vm = viewModel

        viewModel.currentUser.observe(this, Observer<GHUser> { finish() })

    }

    override val layoutId: Int
        get() = R.layout.user_activity_login
}