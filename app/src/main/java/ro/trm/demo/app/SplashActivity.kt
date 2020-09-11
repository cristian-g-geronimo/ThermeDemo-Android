package ro.trm.demo.app

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import ro.trm.demo.tools.BaseActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ro.trm.demo.R
import ro.trm.demo.databinding.ActivitySplashBinding

/**
 * Created by cgheorg1 on 02-Jun-20.
 */
class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivitySplashBinding>(
            this,
            R.layout.activity_splash
        )
        launch { toNextScreen() }
    }

    private suspend fun toNextScreen() {
        Log.d("TAG", "toNextScreen: ")
        delay(1000)
        startActivity(baseContext(), HomeActivity::class.java)
        finish()
    }
}