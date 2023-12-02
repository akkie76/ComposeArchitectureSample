package jp.co.compose.architecture.sample.app

interface ActionObserver {
    fun <T> onDataChanged(action: Action<T>)
}
