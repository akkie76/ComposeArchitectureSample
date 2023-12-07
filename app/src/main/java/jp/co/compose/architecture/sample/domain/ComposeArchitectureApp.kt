package jp.co.compose.architecture.sample.domain

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import jp.co.compose.architecture.sample.domain.search.ui.SearchScreen
import jp.co.compose.architecture.sample.domain.userInfo.ui.UserInfoScreen

private const val SEARCH_ROUTE = "search"
private const val REPOSITORY_ROUTE = "repository"
private const val LOGIN_KEY = "login"

@Composable
fun ComposeArchitectureApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SEARCH_ROUTE) {
        composable(SEARCH_ROUTE) {
            SearchScreen {
                navController.navigate("repository/${it.login}")
            }
        }
        composable(
            "$REPOSITORY_ROUTE/{$LOGIN_KEY}",
            arguments = listOf(
                navArgument(LOGIN_KEY) {
                    type = NavType.StringType
                }
            )
        ) {
            val login = it.arguments?.getString(LOGIN_KEY)
                ?: throw IllegalArgumentException("Login is not specified.")
            UserInfoScreen(
                login = login,
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }
    }
}
