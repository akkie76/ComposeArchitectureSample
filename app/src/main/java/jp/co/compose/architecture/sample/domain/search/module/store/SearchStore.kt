package jp.co.compose.architecture.sample.domain.search.module.store

import jp.co.compose.architecture.sample.app.module.ActionObserver
import jp.co.compose.architecture.sample.domain.search.module.action.SearchAction
import javax.inject.Inject

class SearchStore @Inject constructor() {

    var action: SearchAction = SearchAction.NotLoading()
        private set

    private var observer: ActionObserver? = null

    fun register(actionObserver: ActionObserver) {
        observer = actionObserver
    }

    fun unRegister() {
        observer = null
    }

    fun on(action: SearchAction) {
        this.action = action
        observer?.onDataChanged(action)
    }
}
