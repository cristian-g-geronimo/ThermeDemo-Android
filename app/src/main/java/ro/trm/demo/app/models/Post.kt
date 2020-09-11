package ro.trm.demo.app.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by cgheorg1 on 09-Sep-20.
 */
class Post() : Parcelable {
    @SerializedName("title")
    var title: String? = null

    @SerializedName("body")
    var body: String? = null

    constructor(parcel: Parcel) : this() {
        title = parcel.readString()
        body = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(body)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Post> {
        override fun createFromParcel(parcel: Parcel): Post {
            return Post(parcel)
        }

        override fun newArray(size: Int): Array<Post?> {
            return arrayOfNulls(size)
        }
    }
}