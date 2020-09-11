package ro.trm.demo.tools

import android.content.Context
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by cgheorg1 on 02-Jun-20.
 */
public fun <T, V : BaseListItemView<T>> setupRecyclerView(
    context: Context,
    recyclerView: RecyclerView,
    itemViewClass: Class<V>,
    orientation: Int = RecyclerView.VERTICAL,
    paging: Boolean = false,
    reverseLayout: Boolean = false,
    baseListener: BaseListItemView.BaseListener<T>? = null,
    genericListener: BaseListItemView.GenericListener? = null
): RecyclerBaseAdapter<T, V>? {
    val adapter = RecyclerBaseAdapter.Builder<T, V>()
        .setClass(itemViewClass)
        .setBaseListener(baseListener)
        .setGenericListener(genericListener)
        .setPaging(paging)
        .build()
    recyclerView.layoutManager =
        LinearLayoutManager(context, orientation, reverseLayout)
    recyclerView.adapter = adapter

    return adapter
}

fun setStatusBarColor(color: Int, window: Window) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = color
    }
}

fun hideKeyboard(context: Context, view: View?) {
    val imm =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (imm != null && view != null) {
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}