package osp.leobert.android.github

import android.app.Application
import androidx.room.Room
import osp.leobert.android.github.repo.RepoDatabase

/**
 * <p><b>Package:</b> osp.leobert.android.github </p>
 * <p><b>Classname:</b> GithubApp </p>
 * Created by leobert on 2020/9/9.
 */
class GithubApp : Application() {


    override fun onCreate() {
        super.onCreate()
        RepoDatabase.db =
            Room.databaseBuilder<RepoDatabase>(this, RepoDatabase::class.java, "repo-database")
                .build()
    }
}