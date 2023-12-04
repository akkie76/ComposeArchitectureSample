package jp.co.compose.architecture.sample.domain.search.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import jp.co.compose.architecture.sample.app.data.GithubApiService

class GithubUserPagingSource(
    private val githubApiService: GithubApiService,
    private val query: String
) : PagingSource<Int, GithubUser>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubUser> {
        val page = params.key ?: GITHUB_USER_STARTING_PAGE_INDEX
        return try {
            val response = githubApiService.searchUsers(query, page, params.loadSize)
            val items = response.items
            LoadResult.Page(
                data = items,
                prevKey = if (page == GITHUB_USER_STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page == response.totalCount) null else page + 1
            )
        } catch (exception: Exception) {
            // TODO: ErrorHandling
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GithubUser>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    companion object {
        private const val GITHUB_USER_STARTING_PAGE_INDEX = 1
    }
}
