package osp.leobert.android.github.repo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * <p><b>Package:</b> osp.leobert.android.github.repo </p>
 * <p><b>Classname:</b> GHlogin </p>
 * Created by leobert on 2020/9/9.
 */
@Entity(tableName = "GHLogin")
class GHLogin(
    @field:PrimaryKey
    @field:ColumnInfo(name = "login")
    val login: String,
    @field:ColumnInfo(name = "token")
    val token: String
)