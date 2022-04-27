package com.example.mobile2;

import retrofit2.Call;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.*;

public interface MyApi {
    @HTTP(method = "POST", path = "/", hasBody = true)
    Call<ResponseBody> getData(@Body String result);
}























