package osp.leobert.android.github.user.followers

import androidx.lifecycle.ViewModel
import osp.leobert.android.github.repo.GHUser
import osp.leobert.android.github.repo.api

class FollowersViewModel : ViewModel(), Contract.IViewModel {
    override var page: Int = 1

    override fun fetchFollowers() {
        api(
            request = { GHUser.followers() },
            onSuccess = {

            },
            onFailure = {

            }
        )
    }

}