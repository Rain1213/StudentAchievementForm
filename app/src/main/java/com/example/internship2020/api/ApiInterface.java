package com.example.internship2020.api;

import com.example.internship2020.model.AdminEmail;
import com.example.internship2020.model.Form;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("save.php")
    Call<Form> saveForm(
            @Field("StdID") String stdId,
            @Field("StdName") String stdName,
            @Field("StdSem")  int stdSem,
            @Field("EventType")  String eventType,
            @Field("EventName") String eventNype,
            @Field("FromDate") String fromDate,
            @Field("ToDate") String toDate,
            @Field("ClgScholarship") int clgSch,
            @Field("ExtScholarship") int extSch,
            @Field("DriveLink") String driveLink,
            @Field("Accepted") int accepted
    );

    @GET("requestedForm.php")
    Call<List<Form>> getRequestedForms();

    @GET("approvedForm.php")
    Call<List<Form>> getApprovedForms(@Query("key") String keyword);

    @GET("submittedForm.php")
    Call<List<Form>> getFormsByStdID(@Query("key") String stdID);

    @FormUrlEncoded
    @POST("deleteFromRequest.php")
    Call<Form> deleteForm(
            @Field("ID") int ID

    );

    @FormUrlEncoded
    @POST("approveFormRequest.php")
    Call<Form> archiveForm(
            @Field("ID") int ID

    );

    @GET("adminLog.php")
    Call<AdminEmail> performLogIn(@Query("user_name") String username);



}
