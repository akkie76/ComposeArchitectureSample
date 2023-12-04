package jp.co.compose.architecture.sample.domain.search.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state by viewModel.uiState

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_CREATE) {
                viewModel.onCreate()
            }
            if (event == Lifecycle.Event.ON_DESTROY) {
                viewModel.onDestroy()
            }
        }
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.onSearchRepository()
    }

    Scaffold {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            val pagingItems = viewModel.users.collectAsLazyPagingItems()

            // TODO: Scrollbarを追加する
            LazyColumn {
                items(
                    count = pagingItems.itemCount,
                    key = pagingItems.itemKey()
                ) { index ->
                    val user = pagingItems[index] ?: return@items
                    Text(user.login)
                }
            }
        }
    }
}
