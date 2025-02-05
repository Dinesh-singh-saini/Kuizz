package tech.kuizz.api.interfaces

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import tech.kuizz.api.data.MyApiData


//https://opentdb.com/api.php?amount=10&category=27&difficulty=easy&type=multiple
interface ApiInterface {
    @GET("api.php")
    suspend fun getResult(
        @Query("amount") amount: Int = 10,
        @Query("category") category: Int? = null,
        @Query("difficulty") difficulty: String? = null,
        @Query("type") type: String? = null
    ): Response<MyApiData>
}