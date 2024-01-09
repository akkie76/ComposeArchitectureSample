package jp.co.compose.architecture.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint
import jp.co.compose.architecture.sample.app.ui.theme.ComposeArchitectureSampleTheme
import jp.co.compose.architecture.sample.domain.NavGraphs

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeArchitectureSampleTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}
