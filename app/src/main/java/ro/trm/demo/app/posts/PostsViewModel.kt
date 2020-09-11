package ro.trm.demo.app.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.gxorg.god.tools.network.Resource
import com.gxorg.god.tools.network.Status
import kotlinx.coroutines.Dispatchers
import ro.trm.demo.app.Repository
import ro.trm.demo.app.models.Image
import ro.trm.demo.app.models.Post
import ro.trm.demo.app.network.webservice
import ro.trm.demo.tools.network.ResponseHandler

/**
 * Created by cgheorg1 on 10-Sep-20.
 */
class PostsViewModel : ViewModel() {

    fun getPosts(): LiveData<Resource<List<Post>>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            val response = repository.getPosts()
            if (response.status == Status.ERROR) {
                emit(Resource.success(listOf()))
            } else {
                emit(response)
            }
        }
    }

    fun createPost(post: Post): LiveData<Resource<Post>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            val response = repository.createPost(post)
            emit(response)
        }
    }

    private val client = webservice
    private val repository = Repository(client, ResponseHandler())
}