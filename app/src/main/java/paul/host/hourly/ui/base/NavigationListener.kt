package paulhost.sf.ui

import androidx.fragment.app.Fragment

interface NavigationListener {
    fun goBack()
    fun goTo(fragment: Fragment)
    fun navigateTo(fragment: Fragment)
}
