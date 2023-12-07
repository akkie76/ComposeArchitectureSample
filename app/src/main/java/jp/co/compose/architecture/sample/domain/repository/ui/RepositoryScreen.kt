package jp.co.compose.architecture.sample.domain.repository.ui

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import jp.co.compose.architecture.sample.domain.search.data.GithubUser

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoryScreen(
    viewModel: RepositoryViewModel = hiltViewModel(),
    githubUser: GithubUser
) {
    val context = LocalContext.current as Activity

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Repositories")
                },
                actions = {
                    // TODO: UserIcon
                }
            )
        }
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            Column {
                Button(onClick = {
                    viewModel.onLaunchBrowser(context, "https://github.com/")
                }) {
                    Text("CustomTabs")
                }
            }
        }
    }
}
