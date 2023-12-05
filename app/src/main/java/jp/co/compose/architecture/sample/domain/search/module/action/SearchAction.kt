package jp.co.compose.architecture.sample.domain.search.module.action

import androidx.paging.LoadState
import jp.co.compose.architecture.sample.app.Action

sealed class SearchAction(
    override val data: LoadState,
    override val type: String
) : Action<LoadState> {

    class NotLoading(
        state: LoadState = LoadState.NotLoading(false)
    ) : SearchAction(
        data = state,
        type = TYPE
    ) {
        companion object {
            const val TYPE = "SearchAction.NotLoading"
        }
    }

    class Loading(
        state: LoadState
    ) : SearchAction(
        data = state,
        type = TYPE
    ) {
        companion object {
            const val TYPE = "SearchAction.Loading"
        }
    }

    class Error(
        state: LoadState
    ) : SearchAction(
        data = state,
        type = TYPE
    ) {
        companion object {
            const val TYPE = "SearchAction.Error"
        }
    }
}
