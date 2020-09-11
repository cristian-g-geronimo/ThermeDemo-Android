package ro.trm.demo.tools

import android.content.Context
import android.util.AttributeSet
import com.gxorg.god.tools.BaseFrameLayout

/**
 * Created by cgheorg1 on 05-Jun-20.
 */
abstract class BaseListItemView<T> @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BaseFrameLayout(context, attrs, defStyleAttr), RecyclerBaseAdapter.Assignable<T> {

    protected var mEmptyListener: Any? = null
    protected var mGenericListener: GenericListener? = null
    protected var mListener: BaseListener<T>? = null
    protected var mObject: T? = null

    override fun setObject(`object`: T) {
        mObject = `object`
    }

    open fun setListener(listener: BaseListener<T>?) {
        mListener = listener
    }

    open fun setGenericListener(listener: GenericListener?) {
        mGenericListener = listener
    }

    open fun setEmptyListener(listener: Any?) {
        mEmptyListener = listener
    }

    interface BaseListener<T> {
        fun onListItemClicked(`object`: T)
    }

    interface GenericListener {
        fun onEvent(`object`: Any?)
    }
}