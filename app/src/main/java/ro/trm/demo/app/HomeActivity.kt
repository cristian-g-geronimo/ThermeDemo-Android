package ro.trm.demo.app

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_home.*
import ro.trm.demo.R
import ro.trm.demo.tools.BaseActivity
import java.lang.IllegalStateException

/**
 * Created by cgheorg1 on 09-Sep-20.
 */

class HomeActivity: FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        pager.adapter = HomePagerAdapter(this)
        TabLayoutMediator(tabLayout, pager) { tab, position ->
            tab.text = when(position) {
                0 -> getString(R.string.tab_posts)
                1 -> getString(R.string.tab_images)
                else -> ""
            }
        }.attach()

    }
}