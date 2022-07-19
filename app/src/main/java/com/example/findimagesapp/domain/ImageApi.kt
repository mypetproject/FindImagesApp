package com.example.findimagesapp.domain

import com.example.findimagesapp.dataModels.ImagesResults
import retrofit2.Call
import retrofit2.http.GET

interface ImageApi {

    //@GET("q={query}&tbm=isch")
    @GET("playground?q=Apple&tbm=isch&ijn=0")
   // @GET("search.json?q=Apple&tbm=isch&ijn=0&api_key=0838e7ae1402e544a8980552b72a1d012932ae8e573a7be4718c52ee27104c36")
   // fun getNote(@Path("query") query: String): Call<List<ImageData>>
   // fun getNote(): Call<List<ImagesResults>>
    fun getNote(): Call<ImagesResults>
}