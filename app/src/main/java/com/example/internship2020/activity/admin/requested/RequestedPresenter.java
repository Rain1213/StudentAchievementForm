package com.example.internship2020.activity.admin.requested;

import androidx.annotation.NonNull;

import com.example.internship2020.api.ApiClient;
import com.example.internship2020.api.ApiInterface;
import com.example.internship2020.model.Form;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestedPresenter {

    private RequestedView view;

    public RequestedPresenter(RequestedView view) {
        this.view = view;
    }

    void getData(){
        view.showLoading();
        //Request to Server

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Form>> call = apiInterface.getRequestedForms();
        call.enqueue(new Callback<List<Form>>() {
            @Override
            public void onResponse(@NonNull Call<List<Form>> call,@NonNull Response<List<Form>> response) {
                view.hideLoading();
                if(response.isSuccessful() && response.body() != null){
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Form>> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
        }
        });
    }


}
