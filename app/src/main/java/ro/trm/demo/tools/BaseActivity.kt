package ro.trm.demo.tools

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

/**
 * Created by cgheorg1 on 04-Jun-20.
 */
abstract class BaseActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    open fun startActivity(
        context: Context?,
        calledActivity: Class<*>,
        extras: Bundle? = null,
        action: String? = null,
        flags: Int = 0
    ) {
        context?.let {
            val intent = Intent(it, calledActivity)
            intent.flags = flags
            intent.action = action
            if (extras != null) {
                intent.putExtras(extras)
            }
            it.startActivity(intent)
            if (it is Activity) {
                it.overridePendingTransition(
                    android.R.anim.fade_in,
                    android.R.anim.fade_out
                )
            }
        }
    }

    protected open fun baseContext(): Context? {
        return this
    }
}