package osp.leobert.android.github.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * <p><b>Package:</b> osp.leobert.android.github.base </p>
 * <p><b>Classname:</b> GlideExt </p>
 * Created by leobert on 2020/9/21.
 */
@BindingAdapter("imageUrl")
fun ImageView.imageUrl(url: String?) {
    if (url == null) {
        this.setImageDrawable(null)
        return
    }
    Glide.with(this)
        .asDrawable()
        .load(url)
        .into(this)
}