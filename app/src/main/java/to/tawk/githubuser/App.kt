package to.tawk.githubuser

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

//init Hilt
@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        // init Timber for debugging
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}