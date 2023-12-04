package jp.co.compose.architecture.sample.domain.search.module.action

import androidx.paging.PagingData
import jp.co.compose.architecture.sample.domain.search.data.GithubUser
import jp.co.compose.architecture.sample.domain.search.module.dispatcher.SearchDispatcher
import jp.co.compose.architecture.sample.domain.search.usecase.SearchUsersUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchActionCreatorImpl @Inject constructor(
    private val dispatcher: SearchDispatcher,
    private val searchUsersUseCase: SearchUsersUseCase
) : SearchActionCreator {

    override fun search(query: String): Flow<PagingData<GithubUser>> {
        try {
            val pagingData = searchUsersUseCase.search(query)
            dispatcher.dispatch(SearchAction.Loaded(pagingData))
            return pagingData
        } catch (e: Exception) {
            throw e
        }
    }
}
