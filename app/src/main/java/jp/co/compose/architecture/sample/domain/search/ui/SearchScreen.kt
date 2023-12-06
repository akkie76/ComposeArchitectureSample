package jp.co.compose.architecture.sample.domain.search.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.paging.compose.collectAsLazyPagingItems
import jp.co.compose.architecture.sample.domain.search.module.action.SearchAction

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
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

    val state by viewModel.uiState
    var searchQuery by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            SearchBar(
                text = searchQuery,
                onValueChange = { newValue ->
                    searchQuery = newValue
                    viewModel.onSearchQueryChange(searchQuery)
                },
                onClickLeadingIcon = { newValue ->
                    searchQuery = newValue
                    viewModel.onSearchQueryChange(newValue)
                }
            )
        }
    ) {
        Surface(modifier = Modifier.padding(it)) {
            val pagingItems = viewModel.users.collectAsLazyPagingItems()
            val loadStates = pagingItems.loadState.source

            LaunchedEffect(loadStates) {
                viewModel.onUpdateLoadState(loadStates.refresh)
            }

            if (searchQuery.isEmpty()) {
                Text("Recent Searches")
                return@Surface
            }

            when (state) {
                is SearchAction.NotLoading -> {
                    UsersColumn(pagingItems) {
                        // TODO: Navigate
                    }
                }
                is SearchAction.Loading -> {
                    ProgressIndicator()
                }
                is SearchAction.Error -> {
                    Text("Error")
                }
            }
        }
    }
}
