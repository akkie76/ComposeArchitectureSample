package jp.co.compose.architecture.sample.domain.search.usecase

import androidx.paging.PagingData
import jp.co.compose.architecture.sample.domain.search.data.GithubUser
import jp.co.compose.architecture.sample.domain.search.data.SearchUsersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface SearchUsersUseCase {

    fun search(query: String): Flow<PagingData<GithubUser>>
}

class SearchUsersUseCaseImpl @Inject constructor(
    private val searchUsersRepository: SearchUsersRepository
) : SearchUsersUseCase {

    override fun search(query: String): Flow<PagingData<GithubUser>> {
        return searchUsersRepository.search(query)
    }
}
