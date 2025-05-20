package com.example.petcareapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.example.petcareapp.di.appModule
import com.example.petcareapp.di.databaseModule
import com.example.petcareapp.di.networkModule
import com.example.petcareapp.di.repositoryModule

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(applicationContext)
            modules(appModule,
                databaseModule,
                repositoryModule,
                networkModule)
        }

        setContent {
            PetCareApp()
        }
    }
}
