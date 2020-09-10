package osp.leobert.android.github.user.followers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import osp.leobert.android.github.base.IBaseViewModel
import osp.leobert.android.github.service.IUserComponentService
import osp.leobert.android.github.service.magnetRun
import osp.leobert.android.github.user.R

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

        viewModel.fetchFollowers()
    }

}