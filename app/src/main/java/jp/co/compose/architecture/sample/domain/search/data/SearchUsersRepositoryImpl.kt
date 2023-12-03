package jp.co.compose.architecture.sample.domain.search.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import jp.co.compose.architecture.sample.app.data.GithubApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUsersRepositoryImpl @Inject constructor(
    private val githubApiService: GithubApiService
) : SearchUsersRepository {

    override fun search(query: String): Flow<PagingData<GithubUser>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = MAX_PAGE_SIZE),
            pagingSourceFactory = { GithubUserPagingSource(githubApiService, query) }
        ).flow
    }

    companion object {
        private const val MAX_PAGE_SIZE = 25
    }
}
