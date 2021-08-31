package com.example.hardyapplicationtest.api;



import com.example.hardyapplicationtest.model.DiseaseResult;
import com.example.hardyapplicationtest.model.MembersResult;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface RestService {

    //members
    @GET("/webtest/members.php")
    Call<MembersResult> members();

    //disease
    @GET("/webtest/disease.php")
    Call<DiseaseResult> disease();

}
