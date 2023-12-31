package com.nguyen.hilt1.di

import android.content.Context
import androidx.room.Room
import com.nguyen.hilt1.data.AppDatabase
import com.nguyen.hilt1.data.LogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Since LoggerLocalDataSource is scoped to the application container, the module needs to be
// be installed in the application container using the @InstallIn annotation with SingletonComponent
@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideLogDao(database: AppDatabase): LogDao = database.logDao()

    @Provides
    // always want Hilt to provide the same database instance
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "logging.db").build()
}