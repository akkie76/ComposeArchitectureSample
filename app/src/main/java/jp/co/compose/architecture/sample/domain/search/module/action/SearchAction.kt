package jp.co.compose.architecture.sample.domain.search.module.action

import androidx.paging.PagingData
import jp.co.compose.architecture.sample.app.Action
import jp.co.compose.architecture.sample.domain.search.data.GithubUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

sealed class SearchAction(
    override val data: Flow<PagingData<GithubUser>>,
    override val type: String
) : Action<Flow<PagingData<GithubUser>>> {

    class Initialize(
        override val data: Flow<PagingData<GithubUser>> = flowOf(PagingData.empty())
    ) : SearchAction(
        data = data,
        type = TYPE
    ) {
        companion object {
            const val TYPE = "SearchAction.Initialize"
        }
    }

    class Loaded(
        override val data: Flow<PagingData<GithubUser>>
    ) : SearchAction(
        data = data,
        type = TYPE
    ) {
        companion object {
            const val TYPE = "SearchAction.Loaded"
        }
    }
}
