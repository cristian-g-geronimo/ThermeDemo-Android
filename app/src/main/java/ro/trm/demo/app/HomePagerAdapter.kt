package ro.trm.demo.app

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ro.trm.demo.app.images.ImagesPageFragment
import ro.trm.demo.app.posts.PostsPageFragment

class HomePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> PostsPageFragment()
        1 -> ImagesPageFragment()
        else -> throw IllegalStateException()
    }
}
