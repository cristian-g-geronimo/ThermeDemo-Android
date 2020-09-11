package ro.trm.demo.app.posts

import android.content.Context
import android.util.AttributeSet
import kotlinx.android.synthetic.main.list_item_view_post.view.*
import ro.trm.demo.R
import ro.trm.demo.app.models.Image
import ro.trm.demo.app.models.Post
import ro.trm.demo.tools.BaseListItemView

/**
 * Created by cgheorg1 on 10-Sep-20.
 */
class PostsListItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BaseListItemView<Post>(context, attrs, defStyleAttr) {
    override fun getLayoutResource(): Int {
        return R.layout.list_item_view_post
    }

    override fun setObject(`object`: Post) {
        super.setObject(`object`)
        title.text =  mObject?.title
        body.text = mObject?.body
    }
}