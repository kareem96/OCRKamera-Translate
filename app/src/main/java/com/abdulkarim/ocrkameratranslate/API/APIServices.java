package com.abdulkarim.ocrkameratranslate.API;



import com.abdulkarim.ocrkameratranslate.Model.TranslateResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIServices {
    @POST("translate")
    @FormUrlEncoded
    Call<TranslateResponse> getTranslate(@Field("key") String key,
                                         @Field("text")String text,
                                         @Field("lang") String lang);
}