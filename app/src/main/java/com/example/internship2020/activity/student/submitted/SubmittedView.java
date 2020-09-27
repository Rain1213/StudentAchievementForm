package com.example.internship2020.activity.student.submitted;

import com.example.internship2020.model.Form;

import java.util.List;

public interface SubmittedView {

    void showLoading();
    void hideLoading();
    void onGetResult(List<Form> forms);
    void onErrorLoading(String message);

}
