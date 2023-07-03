package com.nguyen.hilt1.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.nguyen.hilt1.LogApplication
import com.nguyen.hilt1.R
import com.nguyen.hilt1.data.Log
import com.nguyen.hilt1.data.LoggerLocalDataSource
import com.nguyen.hilt1.databinding.FragmentLogsBinding
import com.nguyen.hilt1.util.DateFormatter

/**
 * Fragment that displays the database logs.
 */
class LogsFragment : Fragment(R.layout.fragment_logs) {
    private lateinit var logger: LoggerLocalDataSource
    private lateinit var dateFormatter: DateFormatter
    private lateinit var binding: FragmentLogsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentLogsBinding.bind(view)
        binding.recyclerView.apply {
            setHasFixedSize(true)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        populateFields(context)
    }

    private fun populateFields(context: Context) {
        logger = (context.applicationContext as LogApplication).serviceLocator.loggerLocalDataSource
        dateFormatter = (context.applicationContext as LogApplication).serviceLocator.provideDateFormatter()
    }

    override fun onResume() {
        super.onResume()

        logger.getAllLogs { logs ->
            binding.recyclerView.adapter = LogsViewAdapter(logs, dateFormatter)
        }
    }
}

/**
 * RecyclerView adapter for the logs list.
 */
private class LogsViewAdapter(
    private val logsDataSet: List<Log>,
    private val daterFormatter: DateFormatter
) : RecyclerView.Adapter<LogsViewAdapter.LogsViewHolder>() {

    class LogsViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogsViewHolder {
        return LogsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.text_row_item, parent, false) as TextView
        )
    }

    override fun getItemCount() = logsDataSet.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: LogsViewHolder, position: Int) {
        val log = logsDataSet[position]
        holder.textView.text = "${log.msg}\n\t${daterFormatter.formatDate(log.timestamp)}"
    }
}
