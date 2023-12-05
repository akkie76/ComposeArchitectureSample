package jp.co.compose.architecture.sample.domain.search.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.compose.architecture.sample.app.Action
import jp.co.compose.architecture.sample.app.ActionObserver
import jp.co.compose.architecture.sample.domain.search.module.action.SearchAction
import jp.co.compose.architecture.sample.domain.search.module.action.SearchActionCreator
import jp.co.compose.architecture.sample.domain.search.module.store.SearchStore
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val actionCreator: SearchActionCreator,
    private val store: SearchStore
) : ViewModel(), ActionObserver {

    private val _uiState = mutableStateOf(store.action)
    val uiState: State<SearchAction>
        get() = _uiState

    val users = actionCreator.search("John").cachedIn(viewModelScope)

    fun onCreate() {
        store.register(this)
    }

    fun onDestroy() {
        store.unRegister()
    }

    fun onSearchUsers(query: String) {
        // TODO: dataに変換する
        // actionCreator.searchRepository(query)
    }

    fun onUpdateLoadState(loadState: LoadState) {
        actionCreator.updateState(loadState)
    }

    override fun <T> onDataChanged(action: Action<T>) {
        _uiState.value = action as SearchAction
    }
}
