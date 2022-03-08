package com.jppq.catalog.app

import android.app.Application
import com.jppq.catalog.BuildConfig.BASE_URL
import com.jppq.catalog.core.Config
import com.jppq.catalog.app.modules.RetrofitModule

class CatalogApplication: Application() {

    companion object {

        lateinit var dataModule: RetrofitModule
            private set

        private fun initModules() {
            dataModule = RetrofitModule()
        }
    }

    override fun onCreate() {
        super.onCreate()
        Config.baseUrl = BASE_URL
        initModules()
    }
}