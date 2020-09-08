package osp.leobert.android.github

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.coroutineScope
import osp.leobert.android.github.repo.GHUser
import osp.leobert.android.github.repo.api

class MainActivity : AppCompatActivity() {

    val scope = lifecycle.coroutineScope

    //CoroutineScope(Dispatchers.Main + Job())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.a).setOnClickListener {


            api(scope,
                request = { GHUser.user("leobert-lan").bio },
                onSuccess = {
                    Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
                },
                onFailure = {
                    Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
                })

        }
    }


}