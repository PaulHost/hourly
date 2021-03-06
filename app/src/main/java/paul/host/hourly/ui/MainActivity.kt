package paul.host.hourly.ui

import android.annotation.SuppressLint
import android.os.Bundle
import com.khmelenko.lab.miband.MiBand
import kotlinx.android.synthetic.main.activity_main.*
import paul.host.hourly.R
import paul.host.hourly.ui.base.BaseActivity
import paul.host.hourly.utils.PermissionUtils

@SuppressLint("CheckResult")
class MainActivity : BaseActivity() {
    private lateinit var miBand: MiBand

    override fun container(): Int = main_activity_container.id

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        miBand = MiBand(this)
        PermissionUtils.requestPermissions(this, 1)
            .subscribe {
                startFragment(TestFragment())
            }
    }
}
