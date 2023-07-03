package com.nguyen.hilt1.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.nguyen.hilt1.LogApplication
import com.nguyen.hilt1.R
import com.nguyen.hilt1.data.LoggerLocalDataSource
import com.nguyen.hilt1.navigator.AppNavigator
import com.nguyen.hilt1.navigator.Screens

/**
 * Fragment that displays buttons whose interactions are recorded.
 */
class ButtonsFragment : Fragment() {

    private lateinit var logger: LoggerLocalDataSource
    private lateinit var navigator: AppNavigator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_buttons, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        populateFields(context)
    }

    private fun populateFields(context: Context) {
        logger = (context.applicationContext as LogApplication).
            serviceLocator.loggerLocalDataSource

        navigator = (context.applicationContext as LogApplication).
            serviceLocator.provideNavigator(requireActivity())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.button1).setOnClickListener {
            logger.addLog("Interaction with 'Button 1'")
        }

        view.findViewById<Button>(R.id.button2).setOnClickListener {
            logger.addLog("Interaction with 'Button 2'")
        }

        view.findViewById<Button>(R.id.button3).setOnClickListener {
            logger.addLog("Interaction with 'Button 3'")
        }

        view.findViewById<Button>(R.id.all_logs).setOnClickListener {
            navigator.navigateTo(Screens.LOGS)
        }

        view.findViewById<Button>(R.id.delete_logs).setOnClickListener {
            logger.removeLogs()
        }
    }
}
