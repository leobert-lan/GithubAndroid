package osp.leobert.android.github.repo

import androidx.room.Database
import androidx.room.RoomDatabase
import osp.leobert.android.github.repo.api.GithubUserDao

/**
 * <p><b>Package:</b> osp.leobert.android.github.repo </p>
 * <p><b>Classname:</b> RepoDatabase </p>
 * Created by leobert on 2020/9/9.
 */
@Database(entities = arrayOf(GHUser::class), version = 1, exportSchema = false)
abstract class RepoDatabase : RoomDatabase() {
    companion object {
        var db: RepoDatabase? = null
    }

    abstract fun userDao(): GithubUserDao
}