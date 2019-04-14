package paul.host.hourly.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import io.reactivex.Completable
import paul.host.hourly.application

object PermissionUtils {
    val array = arrayOf(
        Manifest.permission.INTERNET,
        Manifest.permission.BLUETOOTH,
        Manifest.permission.BLUETOOTH_ADMIN,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
//        can be needed:
//        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//        Manifest.permission.READ_EXTERNAL_STORAGE,
//        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    val GRANTED = PackageManager.PERMISSION_GRANTED
    val DENIED = PackageManager.PERMISSION_DENIED

    fun requestPermissions(activity: Activity, requestCode: Int) = Completable.fromAction {
        hasPermissions().let {
            if (it) ActivityCompat.requestPermissions(activity, array, requestCode)
            else Completable.complete()
        }
    }

    fun hasPermissions(
        context: Context = application,
        permissions: Array<String> = array
    ): Boolean = permissions.any {
        hasPermission(context, it)
    }

    fun hasPermission(context: Context, permission: String): Boolean =
        ContextCompat.checkSelfPermission(context, permission) == GRANTED

    fun shouldShowRequestPermissionsRationale(activity: Activity): Boolean = array.any {
        shouldShowRequestPermissionRationale(activity, it)
    }

    fun shouldShowRequestPermissionRationale(activity: Activity, permission: String): Boolean =
        ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)

    fun openSettingsScreen(context: Context = application) =
        context.startActivity(with(Intent()) {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            addCategory(Intent.CATEGORY_DEFAULT)
            data = Uri.parse("package:" + context.packageName)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
        })
}
