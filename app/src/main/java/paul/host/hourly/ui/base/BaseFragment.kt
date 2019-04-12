package paulhost.sf.ui.base

import android.content.Context
import androidx.fragment.app.Fragment
import paulhost.sf.ui.NavigationListener

abstract class BaseFragment : Fragment() {
    var listener: NavigationListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigationListener)
            this.listener = context
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}
