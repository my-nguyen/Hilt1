package com.nguyen.hilt1.di

import com.nguyen.hilt1.data.LoggerDataSource
import com.nguyen.hilt1.data.LoggerInMemoryDataSource
import com.nguyen.hilt1.data.LoggerLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class InMemoryLogger

@Qualifier
annotation class DatabaseLogger

// define 2 different modules, since LoggerInMemoryDataSource is scoped to the Activity container
// and LoggerLocalDataSource to the Application container
@InstallIn(SingletonComponent::class)
@Module
abstract class LoggingDatabaseModule {
    @DatabaseLogger
    @Singleton
    @Binds
    abstract fun bindDatabaseLogger(impl: LoggerLocalDataSource): LoggerDataSource
}

@InstallIn(ActivityComponent::class)
@Module
abstract class LoggingInMemoryModule {
    @InMemoryLogger
    @ActivityScoped
    @Binds
    abstract fun bindInMemoryLogger(impl: LoggerInMemoryDataSource): LoggerDataSource
}
