package com.nguyen.hilt1.data

// Common interface for Logger data sources
interface LoggerDataSource {
    fun addLog(msg: String)
    fun getAllLogs(callback: (List<Log>) -> Unit)
    fun removeLogs()
}