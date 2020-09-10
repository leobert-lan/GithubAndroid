package osp.leobert.android.github.user.followers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import osp.leobert.android.github.base.IBaseViewModel
import osp.leobert.android.github.repo.GHUser
import osp.leobert.android.github.repo.api
import osp.leobert.android.github.service.IUserComponentService
import osp.leobert.android.github.service.magnetRun
import osp.leobert.android.github.service.takeIfInstance
import retrofit2.HttpException

class FollowersViewModel : ViewModel(), Contract.IViewModel {

    override val stateLiveData: MutableLiveData<IBaseViewModel.PagerState> =
        IBaseViewModel.PagerState.createLiveDataState()

    override var page: Int = 1

    override fun fetchFollowers() {
        api(
            request = { GHUser.followers() },
            onSuccess = {

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