package ro.trm.demo.app.images

import android.content.Context
import android.util.AttributeSet
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_view_image.view.*
import ro.trm.demo.R
import ro.trm.demo.app.models.Image
import ro.trm.demo.tools.BaseListItemView

/**
 * Created by cgheorg1 on 10-Sep-20.
 */
class ImageListItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BaseListItemView<Image>(context, attrs, defStyleAttr) {
    private var radius: Int = 0

    init {
        radius = resources.getDimension(R.dimen.default_corner_radius_img).toInt()
    }

    override fun getLayoutResource(): Int {
        return R.layout.list_item_view_image
    }

    override fun setObject(`object`: Image) {
        super.setObject(`object`)
        Picasso.get().load(mObject?.thumbnailUrl).fit()
            .into(image, object : Callback {
                override fun onSuccess() {
                    Picasso.get()
                        .load(mObject?.url)
                        .fit()
                        .into(image)
                }

                override fun onError(e: Exception?) {
                }
            })
    }
}