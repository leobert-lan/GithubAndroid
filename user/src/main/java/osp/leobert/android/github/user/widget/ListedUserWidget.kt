package osp.leobert.android.github.user.widget

import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import osp.leobert.android.github.user.databinding.AppVhListedUserBinding
import osp.leobert.android.pandora.rv.ViewHolderCreator
import osp.leobert.android.github.user.R
import osp.leobert.android.github.base.recyclerview.AbsViewHolder
import osp.leobert.android.pandora.rv.ReactiveData
import osp.leobert.android.pandora.rv.IReactiveViewHolder
import androidx.databinding.Observable
import androidx.databinding.BaseObservable
import osp.leobert.android.github.base.recyclerview.DataBindingViewHolder
import osp.leobert.android.pandora.rv.IViewHolder

import osp.leobert.android.pandora.rv.DataSet

interface ListedUserVO2 : DataSet.Data<ListedUserVO2, AbsViewHolder<ListedUserVO2>>, ReactiveData<ListedUserVO2,AbsViewHolder<ListedUserVO2>>  {
    override fun setToViewHolder(viewHolder: AbsViewHolder<ListedUserVO2>?) {
        viewHolder?.setData(this)
    }

    class Impl : ListedUserVO2 {
        private var viewHolder: IReactiveViewHolder<ListedUserVO2>? = null

        private val observable = BaseObservable().apply {
            addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    viewHolder?.onPropertyChanged(sender ?: this@apply, this@Impl, propertyId)
                }

            })
        }

        override fun bindReactiveVh(viewHolder: IReactiveViewHolder<ListedUserVO2>?) {
            this.viewHolder = viewHolder
        }

        override fun unbindReactiveVh() {
            viewHolder = null
        }
    }
}

class ListedUserVHCreator(private val itemInteract: ListedUserItemInteract?) : ViewHolderCreator() {

    override fun createViewHolder(parent: ViewGroup): DataBindingViewHolder<ListedUserVO2, AppVhListedUserBinding> {
        val binding = DataBindingUtil.inflate<AppVhListedUserBinding>(
            LayoutInflater.from(parent.context),
            R.layout.app_vh_listed_user, parent, false
        )

        val vh = object : DataBindingViewHolder<ListedUserVO2, AppVhListedUserBinding>(binding),IReactiveViewHolder<ListedUserVO2> {

            var mData: ListedUserVO2? = null

            override fun setData(data: ListedUserVO2) {
                super.setData(data)
                mData = data
                binding.vh = this
                binding.vo = data
                binding.executePendingBindings()
            }

            override fun getReactiveDataIfExist(): ReactiveData<out ListedUserVO2, out IViewHolder<ListedUserVO2>>? = mData

            override fun accept(visitor: IViewHolder.Visitor) { visitor.visit(this)}

            override fun onPropertyChanged(sender: Observable?, data: ListedUserVO2, propertyId: Int) {
            }
        }

        binding.vh = vh
        binding.itemInteract = itemInteract

        return vh
    }
}
interface ListedUserItemInteract {
}


