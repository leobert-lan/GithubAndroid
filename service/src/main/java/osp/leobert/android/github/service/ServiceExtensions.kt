package osp.leobert.android.github.service

import osp.leobert.magnet.Magnet
import osp.leobert.magnet.com.IComService

inline fun <reified T : IComService> magnetRun(block: (t: T) -> Unit) {
    Magnet.getInstance().getService(T::class.java)?.let {
        block.invoke(it)
    }
}

inline fun <reified T : IComService, R> magnetRun2(block: (t: T) -> R, default: R): R {
    Magnet.getInstance().getService(T::class.java)?.let {
        return block.invoke(it)
    }
    return default
}