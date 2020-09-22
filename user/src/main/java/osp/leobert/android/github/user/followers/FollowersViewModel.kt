package osp.leobert.android.github.user.followers

import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import osp.leobert.android.github.base.IBaseViewModel
import osp.leobert.android.github.repo.GHUser
import osp.leobert.android.github.repo.api
import osp.leobert.android.github.service.takeIfInstance
import osp.leobert.android.github.user.widget.ListedUserVO2
import osp.leobert.android.pandora.Pandora
import osp.leobert.android.pandora.rv.DataSet
import osp.leobert.android.pandora.rv.PandoraWrapperRvDataSet
import retrofit2.HttpException

class FollowersViewModel : ViewModel(), Contract.IViewModel {

    override val stateLiveData: MutableLiveData<IBaseViewModel.PagerState> =
        IBaseViewModel.PagerState.createLiveDataState()
    override var scope: CoroutineScope = CoroutineScope(Dispatchers.Main + Job())

    override var page: Int = 1
    override var login: String = ""

    override val dataSet: PandoraWrapperRvDataSet<DataSet.Data<*, *>> =
        PandoraWrapperRvDataSet<DataSet.Data<*, *>>(Pandora.wrapper<DataSet.Data<*, *>>())
    private val followerDataSet = Pandora.real<DataSet.Data<*, *>>()

    init {
        dataSet.addSub(followerDataSet)
    }

    override fun fetchFollowers() {
        api(scope = scope,
            request = { GHUser.followersOfUser(login) },
            onSuccess = { list ->
                list?.map { ListedUserVO2.Impl(it) }?.let {
                    followerDataSet.startTransaction()
                    followerDataSet.addAll(it)
                    followerDataSet.endTransaction()
                }
            },
            onFailure = {
                when (it.takeIfInstance<HttpException>()?.code()) {
                    401 -> {
                        stateLiveData.postValue(IBaseViewModel.PagerState.UN_AUTH)
                    }

                    else -> {
                    }
                }
            }
        )
    }

}