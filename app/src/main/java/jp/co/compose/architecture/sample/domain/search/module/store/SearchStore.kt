package jp.co.compose.architecture.sample.domain.search.module.store

import jp.co.compose.architecture.sample.app.ActionObserver
import jp.co.compose.architecture.sample.domain.search.module.action.SearchAction
import javax.inject.Inject

class SearchStore @Inject constructor() {

    var action: SearchAction = SearchAction.Initialize()
        private set

    private var observer: ActionObserver? = null

    fun register(actionObserver: ActionObserver) {
        observer = actionObserver
    }

    fun unRegister() {
        observer = null
    }

    fun on(action: SearchAction) {
        when (action.type) {
            SearchAction.Loaded.TYPE -> {
                this.action = action
            }
            else -> {
                this.action = SearchAction.Initialize()
            }
        }
        observer?.onDataChanged(action)
    }
}
