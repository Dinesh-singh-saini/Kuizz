package tech.kuizz.api.interfaces

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://opentdb.com"

    private fun getInstance(): Retrofit{

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    val quizApi: ApiInterface = getInstance().create(ApiInterface::class.java)

}