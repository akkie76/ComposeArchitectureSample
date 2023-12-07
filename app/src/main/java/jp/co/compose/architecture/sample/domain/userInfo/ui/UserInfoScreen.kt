package jp.co.compose.architecture.sample.domain.userInfo.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
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
            // FIXME: onDestroyされないので、storeにcacheされている
            if (event == Lifecycle.Event.ON_DESTROY) {
                viewModel.onDestroy()
            }
        }
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }

    val state by viewModel.uiState

    LaunchedEffect(login) {
        viewModel.onFetchUserInfo(login)
    }

    Scaffold { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            if (state is UserInfoAction.Ready) {
                HeaderLayout(
                    githubUser = state.data.displayUserInfo,
                    onBackClick = onBackClick
                )
            }

//            Column {
//                Button(onClick = {
//                    viewModel.onLaunchBrowser(context, "https://github.com/")
//                }) {
//                    Text("CustomTabs")
//                }
//            }
        }
    }
}

@Preview
@Composable
private fun PreviewUserInfoScreen() {
    UserInfoScreen(login = "akkie76")
}
