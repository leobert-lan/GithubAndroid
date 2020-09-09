package osp.leobert.android.github.repo.api

import androidx.room.Dao
import androidx.room.Query
import osp.leobert.android.github.repo.GHLogin

/**
 * <p><b>Package:</b> osp.leobert.android.github.repo.api </p>
 * <p><b>Classname:</b> TokenDao </p>
 * Created by leobert on 2020/9/9.
 */
@Dao
interface TokenDao {
    @Query("select * from GHLogin where login = :login")
    fun findToken(login: String): GHLogin?
}