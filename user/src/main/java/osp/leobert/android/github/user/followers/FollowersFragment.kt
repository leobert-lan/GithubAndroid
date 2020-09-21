package osp.leobert.android.github.user.followers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import osp.leobert.android.github.base.IBaseViewModel
import osp.leobert.android.github.service.IUserComponentService
import osp.leobert.android.github.service.magnetRun
import osp.leobert.android.github.user.R
import osp.leobert.android.github.user.widget.ListedUserItemInteract
import osp.leobert.android.github.user.widget.ListedUserVHCreator
import osp.leobert.android.github.user.widget.ListedUserVO2

class FollowersFragment : Fragment(), Contract.IView {

    companion object {
        fun newInstance() = FollowersFragment()
    }

    private lateinit var viewModel: FollowersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_followers_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FollowersViewModel::class.java)

        viewModel.stateLiveData.observe(viewLifecycleOwner, Observer<IBaseViewModel.PagerState> {
            when (it) {
                IBaseViewModel.PagerState.UN_AUTH -> {
// TODO: 2020/9/10 test
                    try {
                        magnetRun<IUserComponentService> { s -> s.navigate2Login(this.requireContext()) }
                    } catch (ignore: Exception) {
                    }
                }
                else -> {

                }
            }
        })

        viewModel.dataSet.registerDVRelation(ListedUserVO2.Impl::class.java,ListedUserVHCreator(
            itemInteract = object:ListedUserItemInteract {

            }
        ))

        lifecycle.coroutineScope.launch {
            delay(3000)
            viewModel.fetchFollowers()
        }
    }

}