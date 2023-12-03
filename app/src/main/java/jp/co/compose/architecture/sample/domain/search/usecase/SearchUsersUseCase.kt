package jp.co.compose.architecture.sample.domain.search.usecase

import androidx.paging.PagingData
import jp.co.compose.architecture.sample.domain.search.data.GithubUser
import kotlinx.coroutines.flow.Flow

interface SearchUsersUseCase {

    fun search(query: String): Flow<PagingData<GithubUser>>
}
