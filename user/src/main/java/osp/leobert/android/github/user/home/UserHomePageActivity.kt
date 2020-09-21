package osp.leobert.android.github.user.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import osp.leobert.android.github.base.BaseActivity
import osp.leobert.android.github.base.adapter.BaseFragmentPagerAdapter
import osp.leobert.android.github.user.R
import osp.leobert.android.github.user.databinding.UserActivityUserHomePageBinding
import osp.leobert.android.github.user.followers.FollowersFragment

class UserHomePageActivity : BaseActivity<UserActivityUserHomePageBinding>(), Contract.IView {

    companion object {
        const val ext_str_login = "ext_str_login"
        fun launch(context: Context, login: String) {
            Intent(context, UserHomePageActivity::class.java)
                .putExtra(ext_str_login, login)
                .let {
                    context.startActivity(it)
                }
        }
    }


    override val layoutId: Int
        get() = R.layout.user_activity_user_home_page

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding?.toolbar?.let {
            setSupportActionBar(it)
        }
        val pagers = mutableListOf<ViewPagerAdapter.Pager>()

        pagers.add(ViewPagerAdapter.Pager(FollowersFragment.newInstance(),"关注"))

        val adapter = ViewPagerAdapter(this.supportFragmentManager, pagers)
        viewBinding?.userHomeVp?.let {
            it.adapter = adapter
        }
    }

    private class ViewPagerAdapter(fm: FragmentManager, val pagers: List<Pager>) :
        BaseFragmentPagerAdapter(
            fm,
            BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        ) {
        class Pager(val fg: Fragment, title: String)

        override fun getItem(position: Int): Fragment = pagers[position].fg

        override fun getCount(): Int = pagers.size

    }
}