package osp.leobert.android.github.user.followers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import osp.leobert.android.github.base.IBaseViewModel
import osp.leobert.android.github.base.adapter.RvAdapter
import osp.leobert.android.github.service.CurrentUser
import osp.leobert.android.github.service.IUserComponentService
import osp.leobert.android.github.service.magnetRun
import osp.leobert.android.github.user.R
import osp.leobert.android.github.user.widget.ListedUserItemInteract
import osp.leobert.android.github.user.widget.ListedUserVHCreator
import osp.leobert.android.github.user.widget.ListedUserVO2
import osp.leobert.android.pandora.Pandora
import osp.leobert.android.pandora.rv.DataSet
import osp.leobert.android.pandora.rv.PandoraWrapperRvDataSet

class FollowersFragment : Fragment(), Contract.IView {

    companion object {
        private const val ext_str_user = "ext_str_user"
        fun newInstance(user: String = CurrentUser.user.value?.login ?: "") =
            FollowersFragment().apply {
                arguments = Bundle().apply {
                    this.putString(ext_str_user, user)
                }
            }
    }

    private lateinit var viewModel: FollowersViewModel
    private lateinit var recyclerView: RecyclerView
    private val user by lazy { arguments?.getString(ext_str_user) ?: "" }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_followers_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FollowersViewModel::class.java)
        viewModel.login = user

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

        viewModel.dataSet.registerDVRelation(ListedUserVO2.Impl::class.java, ListedUserVHCreator(
            itemInteract = object : ListedUserItemInteract {

            }
        ))

        val adapter: RvAdapter<PandoraWrapperRvDataSet<DataSet.Data<*, *>>> =
            RvAdapter(viewModel.dataSet)

        Pandora.bind2RecyclerViewAdapter(viewModel.dataSet.getDataSet(), adapter)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)

        viewModel.fetchFollowers()
    }

}