package com.example.internship2020.activity.admin.requested;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.internship2020.R;
import com.example.internship2020.api.ApiClient;
import com.example.internship2020.api.ApiInterface;
import com.example.internship2020.model.Form;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class confirmMessageApprove extends AppCompatActivity {
    Button ArchiveMe;
    Button GoBack;

    TextView t_message, t_StdId, t_sem, t_title;

    ProgressDialog progressDialogArc;

    String stdId,title;
    int id,sem;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_check);

        progressDialogArc = new ProgressDialog(this);
        progressDialogArc.setMessage("Please wait");

        t_message = findViewById(R.id.txt_deleteOrArchive);
        t_StdId = findViewById(R.id.popup_ans_id);
        t_sem = findViewById(R.id.popup_ans_sem);
        t_title = findViewById(R.id.popup_ans_name);
        ArchiveMe = findViewById(R.id.click_yes);

        Intent approved = getIntent();
        id = approved.getIntExtra("id",0);
        stdId = approved.getStringExtra("stdId");
        sem = approved.getIntExtra("sem",0);
        title = approved.getStringExtra("title");

        t_message.setText("ARE YOU SURE YOU WANT TO ARCHIVE THE FOLLOWING?");
        t_StdId.setText(""+stdId);
        t_sem.setText(""+sem);
        t_title.setText(""+title);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int height = dm.heightPixels;
        int width = dm.widthPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.5));

        ArchiveMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialogArc.show();

                ApiInterface apiInter = ApiClient.getApiClient().create(ApiInterface.class);
                Call<Form> call = apiInter.archiveForm(id);

                call.enqueue(new Callback<Form>() {
                    @Override
                    public void onResponse(@NonNull Call<Form> call, @NonNull Response<Form> response) {

                        progressDialogArc.hide();
                        if(response.isSuccessful() && response.body() !=null){

                            Boolean success = response.body().getSuccess();
                            if(success){
                                Toast.makeText(confirmMessageApprove.this,
                                        response.body().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                                setResult(RESULT_OK);
                                finish();
                            }
                            else {
                                Toast.makeText(confirmMessageApprove.this,
                                        response.body().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Form> call,@NonNull Throwable t) {
                        progressDialogArc.hide();
                        Toast.makeText(confirmMessageApprove.this,
                                t.getLocalizedMessage(),
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });



            }
        });

        GoBack = findViewById(R.id.click_no);
        GoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

    }

}
