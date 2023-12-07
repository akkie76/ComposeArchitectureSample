package jp.co.compose.architecture.sample.domain.userInfo.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun UserInfoScreen(
    viewModel: UserInfoViewModel = hiltViewModel(),
    login: String,
    onBackClick: () -> Unit = {}
) {
    LaunchedEffect(login) {
    }

    Scaffold { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            HeaderLayout(onBackClick = onBackClick)
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