package jp.co.compose.architecture.sample.app.data

import jp.co.compose.architecture.sample.domain.search.data.GithubSearchUsersResponse
import jp.co.compose.architecture.sample.domain.search.data.GithubUser
import jp.co.compose.architecture.sample.domain.userInfo.data.GithubUserInfo
import jp.co.compose.architecture.sample.domain.userInfo.data.RepositoryInfo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {
    /**
     * Searches for GitHub users based on the specified query.
     *
     * @param query The search query.
     * @param page The page number for paginated results.
     * @param perPage The number of items per page.
     * @return A [GithubSearchUsersResponse] containing the search results.
     */
    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): GithubSearchUsersResponse

    /**
     * Fetches information about a specific GitHub user.
     *
     * @param login The GitHub username of the user.
     * @return A [GithubUser] object representing the user's information.
     */
    @GET("users/{login}")
    suspend fun users(
        @Path("login") login: String
    ): GithubUserInfo

    /**
     * Retrieves a list of repositories for the specified GitHub user.
     *
     * @param login The GitHub username of the user.
     * @return A list of [RepositoryInfo] objects representing the user's repositories.
     */
    @GET("users/{login}/repos")
    suspend fun repos(
        @Path("login") login: String
    ): List<RepositoryInfo>
}
