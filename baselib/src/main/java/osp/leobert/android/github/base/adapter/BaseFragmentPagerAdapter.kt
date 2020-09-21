package osp.leobert.android.github.base.adapter

import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter

/**
 * <p><b>Package:</b> osp.leobert.android.github.base.adapter </p>
 * <p><b>Project:</b> GithubAndroid </p>
 * <p><b>Classname:</b> BaseFragmentPagerAdapter </p>
 * Created by leobert on 2020/9/21.
 */
abstract class BaseFragmentPagerAdapter(
    private val mFragmentManager: FragmentManager,
    behavior: Int
) :
    FragmentPagerAdapter(mFragmentManager, behavior) {


    private var tags = SparseArray<String>()

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment =
            super.instantiateItem(container, position) as Fragment
        tags.put(position, fragment.tag)
        return fragment
    }

    fun getFragmentByPosition(position: Int): Fragment? {
        return mFragmentManager.findFragmentByTag(tags.get(position))
    }

    fun getFragments(): List<Fragment?>? {
        return mFragmentManager.fragments
    }

    fun notifyFragmentByPosition(position: Int) {
        tags.removeAt(position)
        notifyDataSetChanged()
    }

    override fun getItemPosition(`object`: Any): Int {
        val fragment = `object` as Fragment
        //如果Item对应的Tag存在，则不进行刷新
        return if (tags.indexOfValue(fragment.tag) > -1) {
            super.getItemPosition(`object`)
        } else PagerAdapter.POSITION_NONE
    }


}