package jp.co.compose.architecture.sample.domain.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.compose.architecture.sample.app.Action
import jp.co.compose.architecture.sample.app.ActionObserver
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val actionCreator: SearchActionCreator,
    private val store: SearchStore
) : ViewModel(), ActionObserver {

    private val _uiState = mutableStateOf(store.data.data)
    val uiState: State<String>
        get() = _uiState

    fun onCreate() {
        store.register(this)
    }

    fun onDestroy() {
        store.unRegister()
    }

    suspend fun onSearchRepository() {
        actionCreator.searchRepository()
    }

    override fun <T> onDataChanged(action: Action<T>) {
        if (action is SearchAction) {
            _uiState.value = action.data as String
        }
    }
}
