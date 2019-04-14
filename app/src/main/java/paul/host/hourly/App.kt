package paul.host.hourly

import android.app.Application
import timber.log.Timber

lateinit var application: Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        application = this
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
