package com.afitech.retrofitdemo.data.api;

import com.afitech.retrofitdemo.data.model.Post;
import com.afitech.retrofitdemo.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserApi {
    @GET("users")
    Call<List<User>> getUserList();

    @POST("posts")
    Call<Post> addPost(@Body Post post);
}
