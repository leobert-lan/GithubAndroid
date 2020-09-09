package osp.leobert.android.github.user.login

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import osp.leobert.android.github.base.BaseActivity
import osp.leobert.android.github.user.R
import osp.leobert.android.github.user.databinding.UserActivityLoginBinding

class LoginActivity : BaseActivity<UserActivityLoginBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)


    }

    override val layoutId: Int
        get() = R.layout.user_activity_login
}