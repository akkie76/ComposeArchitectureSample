package jp.co.compose.architecture.sample.domain.search.module.action

import androidx.paging.LoadState
import androidx.paging.PagingData
import jp.co.compose.architecture.sample.domain.search.data.GithubUser
import jp.co.compose.architecture.sample.domain.search.module.dispatcher.SearchDispatcher
import jp.co.compose.architecture.sample.domain.search.usecase.SearchUsersUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface SearchActionCreator {

    fun search(query: String): Flow<PagingData<GithubUser>>

    fun updateState(loadState: LoadState)
}

class SearchActionCreatorImpl @Inject constructor(
    private val dispatcher: SearchDispatcher,
    private val searchUsersUseCase: SearchUsersUseCase
) : SearchActionCreator {

    override fun search(query: String): Flow<PagingData<GithubUser>> {
        return searchUsersUseCase.search(query)
    }

    override fun updateState(loadState: LoadState) {
        val action = when (loadState) {
            is LoadState.NotLoading -> {
                SearchAction.NotLoading(loadState)
            }
            is LoadState.Loading -> {
                SearchAction.Loading(loadState)
            }
            is LoadState.Error -> {
                SearchAction.Error(loadState)
            }
        }
        dispatcher.dispatch(action)
    }
}
