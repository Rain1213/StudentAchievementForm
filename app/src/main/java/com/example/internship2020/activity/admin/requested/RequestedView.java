package com.example.internship2020.activity.admin.requested;

import com.example.internship2020.model.Form;

import java.util.List;

public interface RequestedView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Form> forms);
    void onErrorLoading(String message);

}
