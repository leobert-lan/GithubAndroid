package osp.leobert.android.github.base.recyclerview

import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.NonNull
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * <p><b>Package:</b> osp.leobert.android.github.base.recyclerview </p>
 * <p><b>Classname:</b> DataBindingViewHolder </p>
 * Created by leobert on 2020/9/21.
 */
abstract class DataBindingViewHolder<T, VDB : ViewDataBinding>(protected val viewDataBinding: VDB,
                                                      rootView: View = viewDataBinding.root) :
    AbsViewHolder<T>(rootView)  {

    override fun asViewHolder(): RecyclerView.ViewHolder {
        return this
    }

    override fun setData(@NonNull data: T) {}

    override fun onViewAttachedToWindow() {}

    @CallSuper
    override fun onViewDetachedFromWindow() {
        super.onViewDetachedFromWindow()
    }
}