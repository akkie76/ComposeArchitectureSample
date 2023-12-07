package jp.co.compose.architecture.sample.domain

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import jp.co.compose.architecture.sample.domain.repository.ui.RepositoryScreen
import jp.co.compose.architecture.sample.domain.search.data.GithubUser.Companion.fromJson
import jp.co.compose.architecture.sample.domain.search.ui.SearchScreen

private const val SEARCH_ROUTE = "search"
private const val REPOSITORY_ROUTE = "repository"
private const val GITHUB_USE_KEY = "githubUser"

@Composable
fun ComposeArchitectureApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SEARCH_ROUTE) {
        composable(SEARCH_ROUTE) {
            SearchScreen {
                // NOTE: https://issuetracker.google.com/issues/309005685
                navController.navigate("repository/${it.toJson()}")
            }
        }
        composable(
            "$REPOSITORY_ROUTE/{$GITHUB_USE_KEY}",
            arguments = listOf(
                navArgument(GITHUB_USE_KEY) {
                    type = NavType.StringType
                }
            )
        ) {
            val json = it.arguments?.getString(GITHUB_USE_KEY)
                ?: throw IllegalArgumentException("GitHub user is not specified.")
            RepositoryScreen(githubUser = json.fromJson())
        }
    }
}
