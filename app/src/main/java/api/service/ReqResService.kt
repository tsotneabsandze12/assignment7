package api.service

import api.dto.ReqResResponse
import api.dto.Resource
import api.dto.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.*
import retrofit2.converter.gson.GsonConverterFactory


interface ReqResService {

    @GET("unknown")
    fun getResources(): Call<ReqResResponse<List<Resource>>>

    @POST("users")
    fun createUser(@Body user: User): Call<User>

    @PUT("users/{id}")
    fun editUser(@Path("id") id: Int?, @Body user: User): Call<User>

}