package osp.leobert.android.github.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * <p><b>Package:</b> osp.leobert.android.github.base </p>
 * <p><b>Project:</b> GithubAndroid </p>
 * <p><b>Classname:</b> BaseActivity </p>
 * Created by leobert on 2020/9/9.
 */
abstract class BaseActivity<VDB : ViewDataBinding> : AppCompatActivity() {

    var viewBinding: VDB? = null

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView<VDB>(this, layoutId)
    }


}