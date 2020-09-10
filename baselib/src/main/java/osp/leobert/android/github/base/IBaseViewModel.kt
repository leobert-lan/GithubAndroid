package osp.leobert.android.github.base

import androidx.lifecycle.MutableLiveData

/**
 * <p><b>Package:</b> osp.leobert.android.github.base </p>
 * <p><b>Project:</b> GithubAndroid </p>
 * <p><b>Classname:</b> IBaseViewModel </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2020/9/10.
 */
interface IBaseViewModel {
    enum class PagerState {
        EMPTY, ERROR, UN_AUTH, LOADING, NORMAL;

        companion object {

            fun createLiveDataState(): MutableLiveData<PagerState> {
                return MutableLiveData()
            }
        }
    }

    val stateLiveData: MutableLiveData<PagerState>
}