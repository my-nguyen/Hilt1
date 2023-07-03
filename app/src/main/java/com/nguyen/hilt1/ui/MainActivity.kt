package com.nguyen.hilt1.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nguyen.hilt1.LogApplication
import com.nguyen.hilt1.R
import com.nguyen.hilt1.navigator.AppNavigator
import com.nguyen.hilt1.navigator.Screens
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main activity of the application.
 *
 * Container for the Buttons & Logs fragments. This activity simply tracks clicks on buttons.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var navigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigator = (applicationContext as LogApplication).serviceLocator.provideNavigator(this)
        if (savedInstanceState == null) {
            navigator.navigateTo(Screens.BUTTONS)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
        }
    }
}
