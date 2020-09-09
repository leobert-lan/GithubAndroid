package osp.leobert.android.github.user.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import osp.leobert.android.github.user.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_activity_login)

        val viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }
}