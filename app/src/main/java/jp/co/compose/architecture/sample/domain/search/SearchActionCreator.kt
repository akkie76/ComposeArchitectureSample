package jp.co.compose.architecture.sample.domain.search

import androidx.paging.PagingData
import jp.co.compose.architecture.sample.domain.search.data.GithubUser
import kotlinx.coroutines.flow.Flow

interface SearchActionCreator {

    fun searchRepository(query: String): Flow<PagingData<GithubUser>>
}
