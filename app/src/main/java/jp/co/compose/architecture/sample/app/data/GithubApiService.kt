package jp.co.compose.architecture.sample.app.data

import jp.co.compose.architecture.sample.domain.search.data.GithubSearchUsersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApiService {

    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): GithubSearchUsersResponse
}
