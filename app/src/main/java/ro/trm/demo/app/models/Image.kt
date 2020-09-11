package ro.trm.demo.app.models

import com.google.gson.annotations.SerializedName

/**
 * Created by cgheorg1 on 09-Sep-20.
 */
class Image {
    @SerializedName("url")
    var url: String? = null

    @SerializedName("thumbnailUrl")
    var thumbnailUrl: String? = null
}