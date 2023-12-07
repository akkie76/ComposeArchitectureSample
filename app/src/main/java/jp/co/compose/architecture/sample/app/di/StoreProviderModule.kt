package jp.co.compose.architecture.sample.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.co.compose.architecture.sample.domain.search.module.store.SearchStore
import jp.co.compose.architecture.sample.domain.userInfo.module.store.UserInfoStore
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StoreProviderModule {

    @Singleton
    @Provides
    fun provideSearchStore(): SearchStore = SearchStore()

    @Singleton
    @Provides
    fun provideUserInfoStore(): UserInfoStore = UserInfoStore()
}
