package com.example.internship2020.activity.student;

public interface FormView {

    void showProgress();
    void hideProgress();
    void onRequestSuccess(String message);
    void onRequestError(String message);

}
