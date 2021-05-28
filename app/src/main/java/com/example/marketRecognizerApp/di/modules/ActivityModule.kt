package com.example.marketRecognizerApp.di.modules

import com.example.marketRecognizerApp.di.scopes.PerActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    @Provides
    @PerActivity
    internal fun provideContext(@ApplicationContext context: ApplicationContext): ApplicationContext {
        return context
    }


}
