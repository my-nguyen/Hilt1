package com.nguyen.hilt1.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.nguyen.hilt1.R
import com.nguyen.hilt1.data.LoggerDataSource
import com.nguyen.hilt1.databinding.FragmentButtonsBinding
import com.nguyen.hilt1.di.InMemoryLogger
import com.nguyen.hilt1.navigator.AppNavigator
import com.nguyen.hilt1.navigator.Screens
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Fragment that displays buttons whose interactions are recorded.
 */
@AndroidEntryPoint
class ButtonsFragment : Fragment(R.layout.fragment_buttons) {
    @InMemoryLogger
    @Inject
    lateinit var logger: LoggerDataSource
    @Inject
    lateinit var navigator: AppNavigator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentButtonsBinding.bind(view)
        binding.button1.setOnClickListener {
            logger.addLog("Interaction with 'Button 1'")
        }

        binding.button2.setOnClickListener {
            logger.addLog("Interaction with 'Button 2'")
        }

        binding.button3.setOnClickListener {
            logger.addLog("Interaction with 'Button 3'")
        }

        binding.allLogs.setOnClickListener {
            navigator.navigateTo(Screens.LOGS)
        }

        binding.deleteLogs.setOnClickListener {
            logger.removeLogs()
        }
    }
}
