package com.example.desafiopicpay

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.desafiopicpay.network.networkModule
import org.koin.core.context.startKoin

class DesafioPicpayApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(
            AppCompatDelegate.MODE_NIGHT_YES)

        startKoin {
            modules(networkModule)
        }
    }
}
