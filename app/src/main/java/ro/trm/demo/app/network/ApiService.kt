package ro.trm.demo.app.network

import retrofit2.http.*
import ro.trm.demo.app.models.Image
import ro.trm.demo.app.models.Post

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("photos")
    suspend fun getImages(): List<Image>

    @POST("posts")
    suspend fun createPost(@Body post: Post): Post

}