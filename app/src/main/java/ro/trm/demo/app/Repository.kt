package ro.trm.demo.app

import ro.trm.demo.app.models.Post
import ro.trm.demo.app.network.ApiService
import ro.trm.demo.tools.network.ResponseHandler

class Repository(
    private val client: ApiService,
    private val responseHandler: ResponseHandler
) {
    suspend fun getImages() =
        responseHandler.handleRequest { client.getImages() }

    suspend fun getPosts() =
        responseHandler.handleRequest { client.getPosts() }

    suspend fun createPost(post: Post) = responseHandler.handleRequest { client.createPost(post) }
}