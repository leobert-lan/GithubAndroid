package osp.leobert.android.github.user.followers

import osp.leobert.android.github.base.IBaseView
import osp.leobert.android.github.base.IBaseViewModel
import osp.leobert.android.pandora.rv.DataSet
import osp.leobert.android.pandora.rv.PandoraWrapperRvDataSet

/**
 * <p><b>Package:</b> osp.leobert.android.github.user.followers </p>
 * <p><b>Project:</b> GithubAndroid </p>
 * <p><b>Classname:</b> Contract </p>
 * Created by leobert on 2020/9/10.
 */
class Contract {
    interface IView : IBaseView {

    }

    interface IViewModel : IBaseViewModel {
        var page: Int

        val dataSet: PandoraWrapperRvDataSet<DataSet.Data<*, *>>

        fun fetchFollowers()

    }
}