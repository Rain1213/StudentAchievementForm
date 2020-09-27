package com.example.internship2020.activity.admin.approved;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.internship2020.R;
import com.example.internship2020.activity.ReviewForm;
import com.example.internship2020.activity.admin.requested.RecyclerViewAdminRequested;
import com.example.internship2020.api.ApiClient;
import com.example.internship2020.api.ApiInterface;
import com.example.internship2020.model.Form;
import com.example.internship2020.model.ReviewStorage;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewAdminApproved extends AppCompatActivity implements ApprovedAdapter.OnFormListener{

    //RecyclerView recyclerView;

    private RecyclerView recyclerView;
    private ApprovedAdapter adapter;
    private ApiInterface apiInterface;
    ProgressBar progressBar;
    TextView search;
    private List<Form> forms;
    String[] item;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_admin_approved);

        progressBar = findViewById(R.id.progress_admin_approved);
        recyclerView = findViewById(R.id.recycler_view_admin_approved);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        fetchForms("");//w/o
    }
    public void fetchForms(String key){
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Form>> call = apiInterface.getApprovedForms(key);

        call.enqueue(new Callback<List<Form>>() {
            @Override
            public void onResponse(Call<List<Form>> call, Response<List<Form>> response) {
                progressBar.setVisibility(View.GONE);
                forms = response.body();
                adapter = new ApprovedAdapter(forms, RecyclerViewAdminApproved.this, RecyclerViewAdminApproved.this::onFormClick);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Form>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(RecyclerViewAdminApproved.this, "Error On: "+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchForms(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fetchForms(newText);
                return false;
            }
        });

        return true;
    }

    @Override
    public void onFormClick(int position) {

        String stdID = forms.get(position).getStdId();
        String stdName = forms.get(position).getStdName();
        int stdSem = forms.get(position).getStdSem();
        String eventType = forms.get(position).getEventType();
        String eventName = forms.get(position).getEventName();
        String dateFrom = forms.get(position).getFromDate();
        String dateTo = forms.get(position).getToDate();
        int clgS = forms.get(position).getClgSch();
        int extS = forms.get(position).getExtSch();
        String link = forms.get(position).getDriveLink();

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


        startActivity(new Intent(RecyclerViewAdminApproved.this, ReviewForm.class));
    }
}

        /*swipeRefreshLayout.setOnRefreshListener(
                () -> presenter.getApprovedData()
        );



        itemClickListener = ((view, position) ->{
            //.......
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


            startActivity(new Intent(RecyclerViewAdminApproved.this, ReviewForm.class));
        });



    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);

    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void onGetResult(List<Form> forms) {
        adapter = new ApprovedAdapter(this, forms, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        form = forms;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}
*/