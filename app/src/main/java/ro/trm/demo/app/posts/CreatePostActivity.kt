package ro.trm.demo.app.posts

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.gxorg.god.tools.network.Status
import kotlinx.android.synthetic.main.activity_create_post.*
import ro.trm.demo.R
import ro.trm.demo.app.models.Post
import ro.trm.demo.tools.BaseActivity

/**
 * Created by cgheorg1 on 11-Sep-20.
 */
const val EXTRA_POST = "EXTRAPOST"

class CreatePostActivity : BaseActivity() {
    private val viewModel: PostsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setTitle(R.string.title_create_post)
        btnCreatePost.setOnClickListener { onCreateClick() }
    }

    private fun onCreateClick() {
        val post = Post()
        post.body = postBody.text.toString().trim()
        post.title = postTitle.text.toString().trim()
        viewModel.createPost(post).observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    setResult(it.data)
                }
                Status.ERROR -> {
                    Toast.makeText(
                        baseContext(),
                        getString(R.string.default_err, it.message),
                        Toast.LENGTH_LONG
                    ).show()
                    showLoading(false)
                }
                Status.LOADING -> showLoading()
            }
        })
    }

    private fun setResult(post: Post?) {
        val intent = Intent()
        intent.putExtra(EXTRA_POST, post)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    private fun showLoading(isLoading: Boolean = true) {
        btnCreatePost.isEnabled = !isLoading
        postBody.isEnabled = !isLoading
        postTitle.isEnabled = !isLoading
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}