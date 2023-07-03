package com.nguyen.hilt1

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.room.Room
import com.nguyen.hilt1.data.AppDatabase
import com.nguyen.hilt1.data.LoggerLocalDataSource
import com.nguyen.hilt1.navigator.AppNavigator
import com.nguyen.hilt1.navigator.AppNavigatorImpl
import com.nguyen.hilt1.util.DateFormatter

class ServiceLocator(applicationContext: Context) {

    private val logsDatabase = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "logging.db")
        .build()

    val loggerLocalDataSource = LoggerLocalDataSource(logsDatabase.logDao())

    fun provideDateFormatter() = DateFormatter()

    fun provideNavigator(activity: FragmentActivity): AppNavigator {
        return AppNavigatorImpl(activity)
    }
}
