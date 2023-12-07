package jp.co.compose.architecture.sample.domain.search.module.action

import androidx.paging.LoadState
import jp.co.compose.architecture.sample.app.Action

sealed class SearchAction(
    override val data: LoadState
) : Action<LoadState> {

    class NotLoading(
        state: LoadState = LoadState.NotLoading(false)
    ) : SearchAction(
        data = state
    )

    class Loading(
        state: LoadState
    ) : SearchAction(
        data = state
    )

    class Error(
        state: LoadState
    ) : SearchAction(
        data = state
    )
}
