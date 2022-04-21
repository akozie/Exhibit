package com.example.seampay.model



import retrofit2.http.GET

interface ExhibitsLoader {

    companion object {
        const val BASE_URL = "https://my-json-server.typicode.com/"
    }

    @GET("Reyst/exhibit_db/list")
    suspend fun getExhibitList(): List<Exhibit>
}