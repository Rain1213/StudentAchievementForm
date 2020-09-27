package com.example.internship2020.activity.student.submitted;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.internship2020.R;
import com.example.internship2020.activity.ReviewForm;
import com.example.internship2020.activity.admin.requested.RecyclerViewAdminRequested;
import com.example.internship2020.model.Form;
import com.example.internship2020.model.ReviewStorage;

import java.util.List;

public class RecyclerViewStudentSubmitted extends AppCompatActivity implements  SubmittedView{

    RecyclerView recyclerViewSub;
    SwipeRefreshLayout swipeRefreshSub;

    SubmittedPresenter presenterSub;
    SubmittedAdapter adapterSub;
    SubmittedAdapter.ItemClickListenerSubmitted itemClickListenerSubmitted;
    List<Form> formSub;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_student);

        swipeRefreshSub = findViewById(R.id.swipe_refresh_student_submitted);
        recyclerViewSub = findViewById(R.id.recycler_view_student);

        recyclerViewSub.setLayoutManager(new LinearLayoutManager(this));

        presenterSub = new SubmittedPresenter(this);
        presenterSub.getData();

        swipeRefreshSub.setOnRefreshListener(
                () -> presenterSub.getData()
        );

        itemClickListenerSubmitted = ((view, position) -> {

            String stdID = formSub.get(position).getStdId();
            String stdName = formSub.get(position).getStdName();
            int stdSem = formSub.get(position).getStdSem();
            String eventType = formSub.get(position).getEventType();
            String eventName = formSub.get(position).getEventName();
            String dateFrom = formSub.get(position).getFromDate();
            String dateTo = formSub.get(position).getToDate();
            int clgS = formSub.get(position).getClgSch();
            int extS = formSub.get(position).getExtSch();
            String link = formSub.get(position).getDriveLink();
            int accept = formSub.get(position).getAccepted();

            String amIAccepted= null;

            if(accept== 0 ){
                amIAccepted= "Decision Pending";
            }else if(accept == 1){
                amIAccepted= "Form Accepted";
            }else if (accept== -1){
                amIAccepted= "Form Rejected";
            }

            Toast.makeText(this,amIAccepted, Toast.LENGTH_SHORT).show();

            ReviewStorage.stdId = stdID;
            ReviewStorage.stdName= stdName;
            ReviewStorage.stdSem = stdSem;
            ReviewStorage.eventType = eventType;
            ReviewStorage.eventName = eventName;
            ReviewStorage.fromDate = dateFrom;
            ReviewStorage.toDate = dateTo;
            ReviewStorage.clgSch = clgS;
            ReviewStorage.extSch = extS;
            ReviewStorage.driveLink = link;


            startActivity(new Intent(RecyclerViewStudentSubmitted.this, ReviewForm.class));



        });


    }

    @Override
    public void showLoading() {
        swipeRefreshSub.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshSub.setRefreshing(false);
    }

    @Override
    public void onGetResult(List<Form> forms) {
        adapterSub = new SubmittedAdapter(this,forms, itemClickListenerSubmitted);
        adapterSub.notifyDataSetChanged();
        recyclerViewSub.setAdapter(adapterSub);

        formSub = forms;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();

    }
}
