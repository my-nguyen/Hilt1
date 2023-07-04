package com.nguyen.hilt1.data

import dagger.hilt.android.scopes.ActivityScoped
import java.util.LinkedList
import javax.inject.Inject

// scope LoggerInMemoryDataSource to the Activity container
@ActivityScoped
class LoggerInMemoryDataSource @Inject constructor(): LoggerDataSource {
    private val logs = LinkedList<Log>()
    override fun addLog(msg: String) {
        logs.addFirst(Log(msg, System.currentTimeMillis()))
    }

    override fun getAllLogs(callback: (List<Log>) -> Unit) {
        callback(logs)
    }

    override fun removeLogs() {
        logs.clear()
    }
}