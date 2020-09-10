package osp.leobert.android.github

import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.coroutineScope
import osp.leobert.android.github.repo.GHUser
import osp.leobert.android.github.repo.RepoDatabase
import osp.leobert.android.github.repo.api
import osp.leobert.android.github.repo.repo
import osp.leobert.android.github.service.IUserComponentService
import osp.leobert.android.github.service.magnetRun
import osp.leobert.magnet.Magnet

class MainActivity : AppCompatActivity() {

    val scope = lifecycle.coroutineScope

    //CoroutineScope(Dispatchers.Main + Job())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.a).setOnClickListener {


            repo(scope,
                request = { GHUser.user("leobert-lan") },
                repoUpdate = {
                    if (it != null)
                        RepoDatabase.db?.userDao()?.insertOrUpdate(it)
                },
                repoRead = {
                    RepoDatabase.db?.userDao()?.findUser("leobert-lan")
                },
                onStart = {
                    Log.e(
                        "lmsg",
                        "start ${Looper.myLooper() == Looper.getMainLooper()}"
                    )
                },
                onSuccess = {
                    Log.e(
                        "lmsg",
                        "${Looper.myLooper() == Looper.getMainLooper()}"
                    )
                    Toast.makeText(this@MainActivity, it?.bio, Toast.LENGTH_SHORT).show()
                },
                onFailure = {
                    Log.e(
                        "lmsg",
                        "${Looper.myLooper() == Looper.getMainLooper()} ${it.message}",
                        it
                    )
                },
                onComplete = {
                    Log.e(
                        "lmsg",
                        "complete ${Looper.myLooper() == Looper.getMainLooper()}"
                    )
                })

        }

        magnetRun<IUserComponentService> { it.navigate2UserHomePage(this,"") }
    }


}