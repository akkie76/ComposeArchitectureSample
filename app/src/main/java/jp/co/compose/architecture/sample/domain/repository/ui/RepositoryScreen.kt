package jp.co.compose.architecture.sample.domain.repository.ui

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RepositoryScreen(
    viewModel: RepositoryViewModel = hiltViewModel()
) {
    val context = LocalContext.current as Activity

    Scaffold { paddingValues ->
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
