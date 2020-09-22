package osp.leobert.android.github.base.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import osp.leobert.android.github.base.recyclerview.AbsViewHolder
import osp.leobert.android.pandora.Logger
import osp.leobert.android.pandora.PandoraException
import osp.leobert.android.pandora.rv.DataSet

/**
 * <p><b>Package:</b> osp.leobert.android.github.base.adapter </p>
 * <p><b>Classname:</b> RvAdapter </p>
 * Created by leobert on 2020/9/21.
 */
class RvAdapter<D : DataSet>(val dataSet: D, val tag: String = "not set") :
    RecyclerView.Adapter<AbsViewHolder<DataSet.Data<*, *>>>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AbsViewHolder<DataSet.Data<*, *>> {
        return try {
            dataSet.createViewHolderV2(parent, viewType) as AbsViewHolder<DataSet.Data<*, *>>
        } catch (e: PandoraException) {
            Logger.e(Logger.TAG, tag, e)
            throw e
        }
    }

    override fun getItemCount(): Int = dataSet.count

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }


    override fun onBindViewHolder(holder: AbsViewHolder<DataSet.Data<*, *>>, position: Int) {
        try {
            DataSet.helpSetToViewHolder<DataSet.Data<*,*>,AbsViewHolder<DataSet.Data<*, *>>>(
                dataSet.getItem(position) as DataSet.D<DataSet.Data<*, *>, AbsViewHolder<DataSet.Data<*, *>>>?, holder
            )
        } catch (e: Exception) {
        }
    }

    override fun getItemViewType(position: Int): Int {
        return try {
            dataSet.getItemViewTypeV2(position)
        } catch (e: PandoraException) {
            Logger.e(Logger.TAG, tag, e)
            -1
        }
    }

    override fun onViewAttachedToWindow(holder: AbsViewHolder<DataSet.Data<*, *>>) {
        super.onViewAttachedToWindow(holder)
        holder.onViewAttachedToWindow()
    }

    override fun onViewDetachedFromWindow(holder: AbsViewHolder<DataSet.Data<*, *>>) {
        super.onViewDetachedFromWindow(holder)
        holder.onViewDetachedFromWindow()
    }

    fun onDataSetChanged() {
        super.notifyDataSetChanged()
    }
}