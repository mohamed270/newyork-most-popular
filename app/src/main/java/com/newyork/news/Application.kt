package com.newyork.news

import com.akexorcist.localizationactivity.ui.LocalizationApplication
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp
import java.util.*

@HiltAndroidApp
class Application: LocalizationApplication() {
    override fun getDefaultLanguage(): Locale = Locale.ENGLISH


    override fun onCreate() {
        super.onCreate()
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());

    }
}