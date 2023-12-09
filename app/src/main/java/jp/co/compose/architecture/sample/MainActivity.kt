package jp.co.compose.architecture.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import jp.co.compose.architecture.sample.app.ui.theme.ComposeArchitectureSampleTheme
import jp.co.compose.architecture.sample.domain.ComposeArchitectureApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeArchitectureSampleTheme {
                ComposeArchitectureApp()
            }
        }
    }
}
