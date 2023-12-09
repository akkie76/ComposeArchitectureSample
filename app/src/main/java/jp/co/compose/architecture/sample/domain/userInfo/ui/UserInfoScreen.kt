package jp.co.compose.architecture.sample.domain.userInfo.ui

import android.app.Activity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import jp.co.compose.architecture.sample.app.ui.ErrorContent
import jp.co.compose.architecture.sample.domain.userInfo.module.action.UserInfoAction
import jp.co.compose.architecture.sample.domain.userInfo.ui.component.InitialContent
import jp.co.compose.architecture.sample.domain.userInfo.ui.component.RepositoryColumn
import kotlinx.coroutines.launch

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
    val coroutineScope = rememberCoroutineScope()

    UserInfoContent(
        state = state,
        onBackClick = onBackClick,
        onSelected = { url ->
            viewModel.onLaunchBrowser(activity, url)
        },
        onRetry = {
            coroutineScope.launch {
                viewModel.onRetry(login)
            }
        }
    )
}

@Composable
fun UserInfoContent(
    state: UserInfoAction,
    onBackClick: () -> Unit = {},
    onSelected: (String) -> Unit = {},
    onRetry: () -> Unit = {}
) {
    Scaffold { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            when (state) {
                is UserInfoAction.Initialize -> {
                    InitialContent(onBackClick = onBackClick)
                }
                is UserInfoAction.Ready -> {
                    RepositoryColumn(
                        state = state,
                        onBackClick = onBackClick,
                        onSelected = onSelected
                    )
                }
                is UserInfoAction.Error -> {
                    ErrorContent(
                        message = state.message,
                        onRetry = onRetry
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewUserInfoScreenContent() {
    UserInfoContent(state = UserInfoAction.Initialize())
}
