package paul.host.hourly.ui.base


import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import paulhost.sf.ui.NavigationListener

abstract class BaseActivity : AppCompatActivity(), NavigationListener {
    protected abstract fun container(): Int

    fun startFragment(fragment: Fragment) = with(supportFragmentManager) {
        beginTransaction()
            .addToBackStack(fragment.javaClass.canonicalName)
            .replace(container(), fragment)
            .commit()
    }


    fun addFragment(fragment: Fragment) = with(supportFragmentManager) {
        beginTransaction()
            .add(container(), fragment)
            .commit()
    }

    override fun goBack() = this.onBackPressed()


    override fun goTo(fragment: Fragment) {
        this.startFragment(fragment)
    }

    override fun navigateTo(fragment: Fragment) {
        this.addFragment(fragment)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            finish()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}
