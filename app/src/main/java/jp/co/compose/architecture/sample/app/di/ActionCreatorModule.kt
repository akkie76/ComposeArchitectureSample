package jp.co.compose.architecture.sample.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.co.compose.architecture.sample.domain.search.module.action.SearchActionCreator
import jp.co.compose.architecture.sample.domain.search.module.action.SearchActionCreatorImpl
import jp.co.compose.architecture.sample.domain.userInfo.module.action.UserInfoActionCreator
import jp.co.compose.architecture.sample.domain.userInfo.module.action.UserInfoActionCreatorImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class ActionCreatorModule {

    @Binds
    abstract fun bindSearchActionCreator(
        searchActionCreatorImpl: SearchActionCreatorImpl
    ): SearchActionCreator

    @Binds
    abstract fun bindUserInfoActionCreator(
        userInfoActionCreatorImpl: UserInfoActionCreatorImpl
    ): UserInfoActionCreator
}
