package jp.co.compose.architecture.sample.domain.search.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _uiState = mutableStateOf(store.data.data)
    val uiState: State<String>
        get() = _uiState

    // TODO: dataに変換する
    val users = actionCreator.searchRepository("john").cachedIn(viewModelScope)

    fun onCreate() {
        store.register(this)
    }

    fun onDestroy() {
        store.unRegister()
    }

    fun onSearchRepository() {
        // TODO: dataに変換する
        actionCreator.searchRepository("john")
    }

    override fun <T> onDataChanged(action: Action<T>) {
        if (action is SearchAction) {
            _uiState.value = action.data as String
        }
    }
}
