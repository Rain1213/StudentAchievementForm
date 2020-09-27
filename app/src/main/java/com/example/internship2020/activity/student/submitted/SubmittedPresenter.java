package com.example.internship2020.activity.student.submitted;

import androidx.annotation.NonNull;

import com.example.internship2020.api.ApiClient;
import com.example.internship2020.api.ApiInterface;
import com.example.internship2020.model.Creds;
import com.example.internship2020.model.Form;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmittedPresenter {

    private SubmittedView viewSub;

    public SubmittedPresenter(SubmittedView view) {
        this.viewSub = view;
    }
    void getData(){
        viewSub.showLoading();
        //Request to server
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Form>> call = apiInterface.getFormsByStdID(Creds.sId);

        call.enqueue(new Callback<List<Form>>() {
            @Override
            public void onResponse(@NonNull Call<List<Form>> call, @NonNull Response<List<Form>> response) {
                viewSub.hideLoading();
                if(response.isSuccessful() && response.body() != null) {
                    viewSub.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure( @NonNull Call<List<Form>> call,@NonNull Throwable t) {
                viewSub.hideLoading();
                viewSub.onErrorLoading(t.getLocalizedMessage());
            }
        });

    }
}
