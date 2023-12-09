package jp.co.compose.architecture.sample.domain.userInfo.ui

import android.app.Activity
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import jp.co.compose.architecture.sample.app.ui.ProgressIndicator
import jp.co.compose.architecture.sample.domain.userInfo.module.action.UserInfoAction

@Composable
fun UserInfoScreen(
    viewModel: UserInfoViewModel = hiltViewModel(),
    login: String,
    onBackClick: () -> Unit = {}
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_CREATE) {
                viewModel.onCreate()
            }
        }
        lifecycle.addObserver(observer)
        onDispose {
            viewModel.onDispose()
            lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(login) {
        viewModel.onFetchUserInfo(login)
    }

    val state by viewModel.uiState
    val activity = LocalContext.current as Activity

    UserInfoScreenContent(
        state = state,
        onBackClick,
        onSelected = { url ->
            viewModel.onLaunchBrowser(activity, url)
        }
    )
}

@Composable
fun UserInfoScreenContent(
    state: UserInfoAction,
    onBackClick: () -> Unit = {},
    onSelected: (String) -> Unit = {}
) {
    Scaffold { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            when (state) {
                is UserInfoAction.Initialize -> {
                    ProgressIndicator()
                }
                is UserInfoAction.Ready -> {
                    LazyColumn {
                        item {
                            HeaderLayout(
                                githubUser = state.data.githubUserInfo,
                                onBackClick = onBackClick
                            )
                        }
                        items(state.data.repositories) { repository ->
                            RepositoryCard(
                                repository = repository,
                                onSelected = onSelected
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
                is UserInfoAction.Error -> {
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewUserInfoScreen() {
    UserInfoScreen(login = "akkie76")
}
