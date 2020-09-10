package osp.leobert.android.github.user.followers

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import osp.leobert.android.github.user.R

class FollowersFragment : Fragment() {

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
        viewModel = ViewModelProviders.of(this).get(FollowersViewModel::class.java)
        // TODO: Use the ViewModel
    }

}