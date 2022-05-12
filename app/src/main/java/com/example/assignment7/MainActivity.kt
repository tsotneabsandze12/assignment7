package com.example.assignment7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import api.Constants
import api.dto.ReqResResponse
import api.dto.Resource
import api.dto.User
import api.service.ReqResService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
   private lateinit var svc: ReqResService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        svc = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build().create(ReqResService::class.java)


        logResources()
        val id = createUser()
        updateUser(id)
    }


    private fun logResources() {
        svc.getResources().enqueue(
            object : Callback<ReqResResponse<List<Resource>>> {
                override fun onResponse(call: Call<ReqResResponse<List<Resource>>>, response: Response<ReqResResponse<List<Resource>>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        response.body()?.data?.forEach { r ->
                            Log.d("resource: ", r.toString())
                        }
                    }
                }

                override fun onFailure(
                    call: Call<ReqResResponse<List<Resource>>>, t: Throwable
                ) {
                }
            }
        )
    }

    private fun createUser() : Int? {
        var id : Int? = null
        svc.createUser(User(id = null, name = "test", job = "test", createdAt = null))
            .enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {

                    if (response.isSuccessful && response.body() != null) {
                        val user: User = response.body() as User
                        Log.d("created user: ", user.toString())
                        id = user.id
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                }
            })
        return id
    }

    private fun updateUser(id: Int?) {
        svc.editUser(id, User(name = "test1", job = "test1", id = null, createdAt = null)).enqueue(
            object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful && response.body() != null) {
                        val user: User = response.body() as User
                        Log.d("created user: ", user.toString())
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                }
            })
    }
}

