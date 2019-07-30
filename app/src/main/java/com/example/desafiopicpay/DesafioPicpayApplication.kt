package com.example.desafiopicpay

import android.app.Application
import com.example.desafiopicpay.network.networkModule
import org.koin.core.context.startKoin

class DesafioPicpayApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(networkModule)
        }
    }
}
