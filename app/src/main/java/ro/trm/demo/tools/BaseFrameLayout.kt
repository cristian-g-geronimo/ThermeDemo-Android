package com.gxorg.god.tools

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created by cgheorg1 on 05-Jun-20.
 */
abstract class BaseFrameLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    protected var binding: ViewDataBinding

    init {
        val layoutInflater = LayoutInflater.from(getContext())
        binding = DataBindingUtil.inflate(
            layoutInflater, getLayoutResource(),
            this,
            true
        )
    }

    @LayoutRes
    protected abstract fun getLayoutResource(): Int

    protected open fun baseContext(): Context? {
        return context
    }

}