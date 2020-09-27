package com.example.internship2020.activity.admin.requested;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.internship2020.R;
import com.example.internship2020.activity.ReviewForm;
import com.example.internship2020.model.Form;
import com.example.internship2020.model.ReviewStorage;

import java.util.List;

public class RecyclerViewAdminRequested extends AppCompatActivity implements RequestedView{

    private static final int INTENT_DELETE = 200;
    private static final int INTENT_ARCHIVE = 100;

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    RequestedPresenter presenter;
    RequestAdapter adapter;
    RequestAdapter.ItemClickListenerRequested itemClickListenerRequested;
    List<Form> form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_admin_requested);

        swipeRefresh = findViewById(R.id.swipe_refresh_admin_requested);
        recyclerView = findViewById(R.id.recycler_view_admin_requested);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new RequestedPresenter(this);
        presenter.getData();

        swipeRefresh.setOnRefreshListener(
                () -> presenter.getData()
        );

        itemClickListenerRequested = ((view, position) -> {

            String stdID = form.get(position).getStdId();
            String stdName = form.get(position).getStdName();
            int stdSem = form.get(position).getStdSem();
            String eventType = form.get(position).getEventType();
            String eventName = form.get(position).getEventName();
            String dateFrom = form.get(position).getFromDate();
            String dateTo = form.get(position).getToDate();
            int clgS = form.get(position).getClgSch();
            int extS = form.get(position).getExtSch();
            String link = form.get(position).getDriveLink();

            Toast.makeText(this,eventType, Toast.LENGTH_SHORT).show();

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


            startActivity(new Intent(RecyclerViewAdminRequested.this, ReviewForm.class));

        }
          );

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);



    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int position = viewHolder.getAdapterPosition();
            int id = form.get(position).getId();
            String stdId = form.get(position).getStdId();
            int sem = form.get(position).getStdSem();
            String title = form.get(position).getEventName();


            switch (direction){
                case ItemTouchHelper.LEFT:

                    form.remove(position);
                    adapter.notifyItemRemoved(position);

                    Intent requested= new Intent(RecyclerViewAdminRequested.this, confirmMessageDelete.class);
                    requested.putExtra("id",id);
                    requested.putExtra("stdId",stdId);
                    requested.putExtra("sem",sem);
                    requested.putExtra("title",title);
                    startActivityForResult(requested, INTENT_DELETE);

                     presenter.getData();
                     swipeRefresh.setOnRefreshListener(
                           () -> presenter.getData()
                     );

                    break;

                case ItemTouchHelper.RIGHT:

                    form.remove(position);
                    adapter.notifyItemRemoved(position);

                    Intent approved= new Intent(RecyclerViewAdminRequested.this, confirmMessageApprove.class);
                    approved.putExtra("id",id);
                    approved.putExtra("stdId",stdId);
                    approved.putExtra("sem",sem);
                    approved.putExtra("title",title);
                    startActivityForResult(approved, INTENT_ARCHIVE);

                    presenter.getData();
                    swipeRefresh.setOnRefreshListener(
                            () -> presenter.getData()
                    );

                    break;

            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == INTENT_DELETE && resultCode == RESULT_OK){
            presenter.getData();
        }

        if(requestCode == INTENT_ARCHIVE && resultCode == RESULT_OK){
            presenter.getData();
        }
    }

    @Override
    public void showLoading() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onGetResult(List<Form> forms) {
        adapter = new RequestAdapter(this,forms,itemClickListenerRequested);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        form = forms;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }



}
