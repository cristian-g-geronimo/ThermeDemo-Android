package com.gxorg.god.tools

/**
 * Created by cgheorg1 on 05-Jun-20.
 */
open class PagedModel {
    fun areContentsTheSame(obj: Any?): Boolean {
        return super.equals(obj)
    }
}