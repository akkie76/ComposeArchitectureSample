package jp.co.compose.architecture.sample.domain.userInfo.ui

import android.app.Activity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import jp.co.compose.architecture.sample.app.ui.ErrorContent
import jp.co.compose.architecture.sample.domain.search.data.GithubUser
import jp.co.compose.architecture.sample.domain.userInfo.module.action.UserInfoAction
import jp.co.compose.architecture.sample.domain.userInfo.ui.component.InitialContent
import jp.co.compose.architecture.sample.domain.userInfo.ui.component.RepositoryColumn

@Destination(navArgsDelegate = GithubUser::class)
@Composable
fun UserInfoScreen(
    viewModel: UserInfoViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
    activity: Activity = LocalContext.current as Activity,
    lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle
) {
    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_CREATE) {
                viewModel.onCreate()
            } else if (event == Lifecycle.Event.ON_START) {
                viewModel.onFetchUserInfo()
            }
        }
        lifecycle.addObserver(observer)
        onDispose {
            viewModel.onDispose()
            lifecycle.removeObserver(observer)
        }
    }

    val state by viewModel.uiState

    UserInfoContent(
        state = state,
        onBackClick = { navigator.navigateUp() },
        onSelect = { viewModel.onLaunchBrowser(activity, it) },
        onRetry = { viewModel.onRetry() }
    )
}

@Composable
fun UserInfoContent(
    state: UserInfoAction,
    onBackClick: () -> Unit = {},
    onSelect: (String) -> Unit = {},
    onRetry: () -> Unit = {}
) {
    Scaffold { paddingValues ->
        when (state) {
            is UserInfoAction.Initialize -> {
                InitialContent(onBackClick = onBackClick)
            }
            is UserInfoAction.Ready -> {
                RepositoryColumn(
                    modifier = Modifier.padding(paddingValues),
                    userInfo = state.data,
                    onBackClick = onBackClick,
                    onSelect = onSelect
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

@Preview
@Composable
private fun PreviewUserInfoScreenContent() {
    UserInfoContent(state = UserInfoAction.Initialize())
}
