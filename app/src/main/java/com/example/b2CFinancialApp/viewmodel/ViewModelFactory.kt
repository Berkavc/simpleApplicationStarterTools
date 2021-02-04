package com.example.b2CFinancialApp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST", "TooGenericExceptionCaught", "TooGenericExceptionThrown")
class ViewModelFactory
@Inject constructor(
    private val creators: Map<Class<out ViewModel>,
            @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.asIterable().firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: run {
            Timber.e("You might forgotten to add viewmodel:${modelClass.name} to ViewModelModule class!! ")
            throw IllegalArgumentException("Unknown ViewModel class $modelClass")
        }
        return try {
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)

        }
    }
}
