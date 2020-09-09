package osp.leobert.android.github.repo

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * <p><b>Package:</b> osp.leobert.android.github.repo </p>
 * <p><b>Classname:</b> RepoFlow </p>
 * Created by leobert on 2020/9/8.
 */
fun <T> api(
    scope: CoroutineScope,
    request: suspend () -> T,
    onStart: (suspend () -> Unit)? = null,
    onSuccess: (suspend (value: T) -> Unit),
    onFailure: (suspend FlowCollector<T>.(cause: Throwable) -> Unit)?,
    onComplete: (suspend () -> Unit)? = null
) {
    scope.launch {
        flow {
            val bean = request.invoke()
            emit(bean)
        }
            .flowOn(Dispatchers.IO)
            .onStart { onStart?.invoke() }
            .catch(onFailure ?: {})
            .onCompletion { onComplete?.invoke() }
            .flowOn(Dispatchers.Main)
            .collect(onSuccess)
    }
}


fun <T> repo(
    scope: CoroutineScope,
    request: suspend () -> T,
    repoRead: (suspend () -> T)?,
    repoUpdate: (suspend (value: T) -> Unit)?,
    onStart: (suspend () -> Unit)? = null,
    onSuccess: (suspend (value: T) -> Unit),
    onFailure: (suspend FlowCollector<T>.(cause: Throwable) -> Unit)?,
    onComplete: (suspend () -> Unit)? = null
) {
    scope.launch {
        flow {
            try {
                val bean = request.invoke()
                emit(bean)
            } catch (e:Exception) {
                repoRead?.invoke()?.let { t ->
                    this.emit(t)
                }?:throw e
            }
        }
            .onEach { repoUpdate?.invoke(it) }
//            .catch {
//                repoRead?.invoke()?.let { t ->
//                    this.emit(t)
//                }
//            }
            .flowOn(Dispatchers.IO)
            .onStart { onStart?.invoke() }
            .catch(onFailure ?: {})
            .onCompletion { onComplete?.invoke() }
            .flowOn(Dispatchers.Main)
            .collect(onSuccess)
    }
}
