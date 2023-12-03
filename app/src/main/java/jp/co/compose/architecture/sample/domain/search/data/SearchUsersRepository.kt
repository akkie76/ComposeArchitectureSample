package jp.co.compose.architecture.sample.domain.search.data

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface SearchUsersRepository {

    fun search(query: String): Flow<PagingData<GithubUser>>
}
