package osp.leobert.android.github.base.recyclerview

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import osp.leobert.android.pandora.rv.IViewHolder

/**
 * <p><b>Package:</b> osp.leobert.android.github.base.recyclerview </p>
 * <p><b>Classname:</b> AbsViewHolder </p>
 * Created by leobert on 2020/9/21.
 */
abstract class AbsViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView), IViewHolder<T> {
    override fun asViewHolder(): RecyclerView.ViewHolder {
        return this
    }

    protected fun getContext(): Context {
        return itemView.context
    }

    override fun onViewAttachedToWindow() {

    }

    override fun onViewDetachedFromWindow() {

    }
}