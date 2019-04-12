package paul.host.hourly.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khmelenko.lab.miband.MiBand
import com.khmelenko.lab.miband.model.VibrationMode
import kotlinx.android.synthetic.main.fragment_test.*
import paul.host.hourly.R
import paulhost.sf.ui.base.BaseFragment
import timber.log.Timber

class TestFragment : BaseFragment() {
    private var miBand: MiBand? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_test, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        miBand = context?.let { MiBand(it) }

        vibrate.setOnClickListener {
            miBand?.startScan()
                ?.flatMap { miBand?.connect(it.device) }
                ?.flatMap { miBand?.startVibration(VibrationMode.VIBRATION_WITH_LED) }
                ?.subscribe(
                    { Timber.d("success") },
                    { Timber.e(it) }
                )
        }
    }
}
