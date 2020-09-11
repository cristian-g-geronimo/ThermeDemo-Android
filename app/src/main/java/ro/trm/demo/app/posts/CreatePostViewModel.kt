package ro.trm.demo.app.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.gxorg.god.tools.network.Resource
import kotlinx.coroutines.Dispatchers
import ro.trm.demo.app.Repository
import ro.trm.demo.app.models.Post
import ro.trm.demo.app.network.webservice
import ro.trm.demo.tools.network.ResponseHandler

/**
 * Created by cgheorg1 on 11-Sep-20.
 */
class CreatePostViewModel : ViewModel() {
    private val client = webservice
    private val repository = Repository(client, ResponseHandler())

    fun createPost(post: Post): LiveData<Resource<Post>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            val response = repository.createPost(post)
            emit(response)
        }
    }
}