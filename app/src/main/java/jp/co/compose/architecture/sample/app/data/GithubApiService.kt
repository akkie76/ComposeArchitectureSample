package jp.co.compose.architecture.sample.app.data

import jp.co.compose.architecture.sample.domain.search.data.GithubSearchUsersResponse
import jp.co.compose.architecture.sample.domain.search.data.GithubUser
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
    ): GithubUser

    /**
     * Retrieves a list of followers for the specified GitHub user.
     *
     * @param login The GitHub username of the user.
     * @return A list of [GithubUser] objects representing the followers.
     */
    @GET("users/{login}/followers")
    suspend fun followers(
        @Path("login") login: String
    ): List<GithubUser>

    /**
     * Retrieves a list of users that the specified GitHub user is following.
     *
     * @param login The GitHub username of the user.
     * @return A list of [GithubUser] objects representing the users being followed.
     */
    @GET("users/{login}/following")
    suspend fun following(
        @Path("login") login: String
    ): List<GithubUser>
}
