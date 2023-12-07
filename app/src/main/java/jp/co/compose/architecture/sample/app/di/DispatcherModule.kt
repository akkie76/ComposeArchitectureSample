package jp.co.compose.architecture.sample.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.co.compose.architecture.sample.domain.search.module.dispatcher.SearchDispatcher
import jp.co.compose.architecture.sample.domain.search.module.dispatcher.SearchDispatcherImpl
import jp.co.compose.architecture.sample.domain.userInfo.module.dispatcher.UserInfoDispatcher
import jp.co.compose.architecture.sample.domain.userInfo.module.dispatcher.UserInfoDispatcherImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatcherModule {

    @Binds
    abstract fun bindSearchDispatcher(
        searchDispatcher: SearchDispatcherImpl
    ): SearchDispatcher

    @Binds
    abstract fun bindUserInfoDispatcher(
        userInfoDispatcherImpl: UserInfoDispatcherImpl
    ): UserInfoDispatcher
}
