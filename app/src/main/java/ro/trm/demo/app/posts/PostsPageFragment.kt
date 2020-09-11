package ro.trm.demo.app.posts

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.gxorg.god.tools.network.Status
import kotlinx.android.synthetic.main.fragment_page_posts.*
import ro.trm.demo.R
import ro.trm.demo.app.models.Post
import ro.trm.demo.tools.RecyclerBaseAdapter
import ro.trm.demo.tools.setupRecyclerView

const val REQUEST_POST = 123

class PostsPageFragment : Fragment() {
    private var adapter: RecyclerBaseAdapter<Post, PostsListItemView>? = null
    private val viewModel: PostsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_page_posts, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnAdd.setOnClickListener { onAddClick() }

        context?.let {
            adapter = setupRecyclerView(it, posts, PostsListItemView::class.java)
        }

        viewModel.getPosts().observe(viewLifecycleOwner, Observer { resource ->
            when (resource.status) {
                Status.ERROR -> {
                    showLoading(false)
                }
                Status.LOADING -> {
                    showLoading()
                }
                Status.SUCCESS -> {
                    showLoading(false)
                    adapter?.setObjectList(resource.data)
                }
            }
        })
    }

    private fun showLoading(show: Boolean = false) {

    }

    private fun onAddClick() {
        val intent = Intent(context, CreatePostActivity::class.java)
        startActivityForResult(intent, REQUEST_POST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_POST) {
            if (resultCode == RESULT_OK) {
                val post = data?.extras?.getParcelable<Post>(EXTRA_POST)
                adapter?.insertFirst((post))
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
