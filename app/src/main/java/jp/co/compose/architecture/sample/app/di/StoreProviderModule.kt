package jp.co.compose.architecture.sample.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.co.compose.architecture.sample.domain.search.SearchStore
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StoreProviderModule {

    @Singleton
    @Provides
    fun provideSearchStore(
        // searchDispatcher: SearchDispatcherImpl
    ) = SearchStore()
}
