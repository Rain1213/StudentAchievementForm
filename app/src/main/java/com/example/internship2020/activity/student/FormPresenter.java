package com.example.internship2020.activity.student;

import androidx.annotation.NonNull;

import com.example.internship2020.api.ApiClient;
import com.example.internship2020.api.ApiInterface;
import com.example.internship2020.model.Form;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormPresenter {

    private FormView view;

    public FormPresenter(FormView view){
        this.view = view;

    }

    void saveData(final String stdId, final String stdName, final int stdSem, final String eventType, final String eventName, final String fromDate, final String toDate, final int clgSch, final int extSch, final String driveLink, final int accepted) {

        view.showProgress();

        ApiInterface apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        Call<Form> call = apiInterface.saveForm(stdId, stdName, stdSem, eventType, eventName, fromDate, toDate, clgSch, extSch, driveLink, accepted);

        call.enqueue(new Callback<Form>() {
            @Override
            public void onResponse(@NonNull Call<Form> call, @NonNull Response<Form> response) {
                view.hideProgress();

                if(response.isSuccessful() && response.body()!=null){
                    Boolean success = response.body().getSuccess();

                    if(success){
                        view.onRequestSuccess(response.body().getMessage());

                    }
                    else{
                        view.onRequestError(response.body().getMessage());
                        // if error still in same activity

                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Form> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());

            }
        });



    }

}


