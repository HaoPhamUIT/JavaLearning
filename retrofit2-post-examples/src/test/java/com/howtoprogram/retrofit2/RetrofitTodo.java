package com.howtoprogram.retrofit2;

import com.object.Todo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

/**
 * Created by my-tran on 5/26/17.
 */
public interface RetrofitTodo {

    @GET("todos/1")
    Call<Todo> getTodo();

    @POST("todos")
    Call<Todo> postTodo(@Body Todo todo);

    @GET("todos")
    Call<List<Todo>> getListTodo();

}
