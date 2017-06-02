package com.howtoprogram.retrofit2;

import com.object.ResponseBodyEntity;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by my-tran on 5/26/17.
 */
public interface RetrofitClient {

    /**
     * Send GET request to baseUrl+"ip"
     */
    @GET("ip")
    Call<ResponseBodyEntity> getIp();

    @GET("posts/1")
    Call<ResponseBodyEntity> get1post();

}
