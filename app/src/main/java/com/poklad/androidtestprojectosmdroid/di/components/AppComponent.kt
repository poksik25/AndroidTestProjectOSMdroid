package com.poklad.androidtestprojectosmdroid.di.components
import NetworkModule
import android.content.Context
import com.poklad.androidtestprojectosmdroid.di.annotations.ApplicationScope
import com.poklad.androidtestprojectosmdroid.di.module.DispatcherModule
import com.poklad.androidtestprojectosmdroid.di.module.RepositoryModule
import com.poklad.androidtestprojectosmdroid.di.viewModel.ViewModelModule
import com.poklad.androidtestprojectosmdroid.presentation.MainActivity
import com.poklad.jobinterviewtestproject.di.viewModel.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DispatcherModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)

}