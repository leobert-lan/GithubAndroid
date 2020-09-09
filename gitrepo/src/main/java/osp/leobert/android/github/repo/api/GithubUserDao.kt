package osp.leobert.android.github.repo.api

import androidx.room.*
import osp.leobert.android.github.repo.GHUser

/**
 * <p><b>Package:</b> osp.leobert.android.github.repo.api </p>
 * <p><b>Classname:</b> GithubUserDao </p>
 * Created by leobert on 2020/9/9.
 */
@Dao
interface GithubUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(user:GHUser)

    @Query("SELECT * FROM GHUser where login = :login")
    fun findUser(login:String):GHUser?
}