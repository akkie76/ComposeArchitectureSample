package jp.co.compose.architecture.sample.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.co.compose.architecture.sample.app.data.ApiClient
import jp.co.compose.architecture.sample.app.data.GithubApiService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideGithubApiService(): GithubApiService = ApiClient.create()
}
