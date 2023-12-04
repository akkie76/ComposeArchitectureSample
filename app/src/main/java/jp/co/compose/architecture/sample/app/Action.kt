package jp.co.compose.architecture.sample.app

interface Action<out T> {
    val data: T
    val type: String
}
