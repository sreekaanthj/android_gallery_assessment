package com.dazn.assessment.gallery

import android.app.Application
import android.content.pm.ApplicationInfo
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


@HiltAndroidApp
class GalleryApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
//        if(isDebugBuild()) {
        Timber.plant(Timber.DebugTree())
//        }

    }

    private fun isDebugBuild() =
        (applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) == ApplicationInfo.FLAG_DEBUGGABLE
}
