package com.nguyen.hilt1.di

import com.nguyen.hilt1.navigator.AppNavigator
import com.nguyen.hilt1.navigator.AppNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

// install in an Activity container, because AppNavigatorImpl has an Activity as a dependency
@InstallIn(ActivityComponent::class)
@Module
// Because AppNavigator is an interface, we cannot use constructor injection. instead, we must use
// @Binds annotation inside a Hilt module to specify the implementation
abstract class NavigationModule {
    // @Binds annotates an abstract function
    @Binds
    abstract fun bindNavigator(impl: AppNavigatorImpl): AppNavigator
}