package com.vimalcvs.myapplication.repository


import android.util.Log
import com.vimalcvs.myapplication.data.ModelLive
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepository @Inject constructor(private val client: HttpClient) {

    private val TAG = "ApiRepository"

    suspend fun getPosts(): ModelLive {
        return try {
            Log.d(TAG, "Fetching posts from API...")
            val response: HttpResponse = client.get("liveapi.php")
            Log.d(TAG, "API response received: ${response.status}")

            val college: ModelLive = response.body()
            Log.d(TAG, "Parsed College data: $college")

            college
        } catch (e: Exception) {
            Log.e(TAG, "Error fetching posts: ${e.message}", e)
            throw e
        }
    }
}