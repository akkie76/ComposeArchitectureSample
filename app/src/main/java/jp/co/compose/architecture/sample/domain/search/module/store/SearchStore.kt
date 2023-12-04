package jp.co.compose.architecture.sample.domain.search.module.store

import jp.co.compose.architecture.sample.app.ActionObserver
import jp.co.compose.architecture.sample.domain.search.module.action.SearchAction
import javax.inject.Inject

class SearchStore @Inject constructor() {

    var data: SearchAction<String> = SearchAction.Initialize("initialize")
        private set

    private var observer: ActionObserver? = null

    fun register(actionObserver: ActionObserver) {
        observer = actionObserver
    }

    fun unRegister() {
        observer = null
    }

    fun on(action: SearchAction<String>) {
        data = action
        observer?.onDataChanged(data)
    }
}
