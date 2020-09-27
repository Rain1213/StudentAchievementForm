package com.example.internship2020.activity.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.internship2020.R;
import com.example.internship2020.activity.admin.approved.RecyclerViewAdminApproved;
import com.example.internship2020.activity.admin.requested.RecyclerViewAdminRequested;
import com.example.internship2020.activity.main.MainActivity;
import com.example.internship2020.api.ApiClient;
import com.example.internship2020.api.ApiInterface;
import com.example.internship2020.model.AdminEmail;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminMainPage extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
    CardView card_requested;
    CardView card_approved;
    ImageView logout;
    TextView nameAdmin;

    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;
    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main_page);
        card_requested=findViewById(R.id.card_requested);
        card_approved = findViewById(R.id.card_approved);
        logout = findViewById(R.id.logout);
        nameAdmin = findViewById(R.id.textView);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API , gso).build();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        if(status.isSuccess()) {

                            gotoMainActivity();
                            finish();

                        }
                        else{
                            Toast.makeText(AdminMainPage.this,"Log Out Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    private void gotoMainActivity() {
        Toast.makeText(AdminMainPage.this,"Logged Out Successfully",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(AdminMainPage.this, MainActivity.class));
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);

        if(opr.isDone()){
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else{
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult result) {
                    handleSignInResult(result);
                }
            });
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();

            String email= account.getEmail();
            performLgn(email);

        }
    }
    private void performLgn(String email){
        Call<AdminEmail> call =  apiInterface.performLogIn(email);



        call.enqueue(new Callback<AdminEmail>() {
            @Override
            public void onResponse(@NonNull Call<AdminEmail> call, @NonNull Response<AdminEmail> response) {
                if(response.body().getResponse().equals("ok")){
                    Toast.makeText(AdminMainPage.this,"Admin Login Successful",Toast.LENGTH_SHORT).show();

                    card_requested.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent requested = new Intent(AdminMainPage.this, RecyclerViewAdminRequested.class);
                            startActivity(requested);
                        }
                    });


                    card_approved.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent approved = new Intent(AdminMainPage.this, RecyclerViewAdminApproved.class);
                            startActivity(approved);
                        }
                    });

                }
                else{
                    nameAdmin.setTextColor(Color.parseColor("#EE0000"));
                    nameAdmin.setText("UNAUTHORISED ACCESS");
                    Toast.makeText(AdminMainPage.this,"Log Out and Sign In properly",Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<AdminEmail> call, Throwable t) {
                Toast.makeText(AdminMainPage.this,"Internal Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
