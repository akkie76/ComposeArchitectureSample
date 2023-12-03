package jp.co.compose.architecture.sample.domain.search

import androidx.paging.PagingData
import jp.co.compose.architecture.sample.domain.search.data.GithubUser
import jp.co.compose.architecture.sample.domain.search.usecase.SearchUsersUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchActionCreatorImpl @Inject constructor(
    private val dispatcher: SearchDispatcher,
    private val searchUsersUseCase: SearchUsersUseCase
) : SearchActionCreator {

    override fun searchRepository(query: String): Flow<PagingData<GithubUser>> {
        val pagingData = searchUsersUseCase.search(query)
        dispatcher.dispatch()
        return pagingData
    }
}
