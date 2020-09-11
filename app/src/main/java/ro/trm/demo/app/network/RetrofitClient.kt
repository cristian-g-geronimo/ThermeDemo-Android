package ro.trm.demo.app.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*

val webservice: ApiService by lazy {
    Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com")
        .addConverterFactory(GsonConverterFactory.create(generateDefaultGsonBuilder().create()))
        .client(httpClient)
        .build()
        .create(ApiService::class.java)
}

val httpClient: OkHttpClient by lazy {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
}

fun generateDefaultGsonBuilder(): GsonBuilder {
    return GsonBuilder()
        .setLenient()
        .setDateFormat("yyyy-MM-dd HH:mm:ss")
        .disableHtmlEscaping()
}