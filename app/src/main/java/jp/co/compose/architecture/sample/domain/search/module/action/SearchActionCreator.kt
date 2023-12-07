package jp.co.compose.architecture.sample.domain.search.module.action

import androidx.paging.LoadState
import androidx.paging.PagingData
import jp.co.compose.architecture.sample.domain.search.data.GithubUser
import kotlinx.coroutines.flow.Flow

interface SearchActionCreator {

    fun search(query: String): Flow<PagingData<GithubUser>>

    fun updateState(loadState: LoadState)

    suspend fun fetchUser(login: String)
}
