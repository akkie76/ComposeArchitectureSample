package jp.co.compose.architecture.sample.app.data

import jp.co.compose.architecture.sample.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newRequest = request.newBuilder()
            .header("Accept", "application/vnd.github+json")
            .header("Authorization", "Bearer ${BuildConfig.GITHUB_TOKEN}")
            .header("X-GitHub-Api-Version", "2022-11-28")
            .build()

        return chain.proceed(newRequest)
    }
}
