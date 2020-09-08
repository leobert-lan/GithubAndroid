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
//class RepoFlow {
//}

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
            .onStart { onStart?.invoke() }
            .catch(onFailure ?: {})
            .onCompletion { onComplete?.invoke() }
            .flowOn(Dispatchers.IO)
            .collect(onSuccess)
    }
}

