package com.example.internship2020.activity.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.internship2020.R;
import com.example.internship2020.activity.main.MainActivity;
import com.example.internship2020.activity.student.submitted.RecyclerViewStudentSubmitted;
import com.example.internship2020.model.Creds;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;


public class StudentMainPage extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    CardView card_form;
    CardView card_submitted;
    ImageView logout;
    TextView name;
    TextView id;
    String sId;
    String sName;
    //TextView ans_name;

    //Google Sign In Variables
    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main_page);


        card_form=findViewById(R.id.card_requested);
        card_submitted=findViewById(R.id.card_approved);
        logout = findViewById(R.id.logout);
        name = findViewById(R.id.student_name);
        id = findViewById(R.id.student_id);


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
                            Toast.makeText(StudentMainPage.this,"Log Out Failed", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(StudentMainPage.this,"Logged Out Successfully",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(StudentMainPage.this, MainActivity.class));
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


    private void handleSignInResult(GoogleSignInResult result){
        if(result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();

            /*
            Once SignIn is successful and possible, app would let emails proceed only if they have
            email ending with @charusat.edu.in
            */

            String email1 = account.getEmail();
            String[] e_split = email1.split("@");
            String domain = e_split[1];

            //Storing ID from email address into Cred Class
            sId = e_split[0];
            Creds.sId = sId;

            if(domain.equalsIgnoreCase("charusat.edu.in")){

                //Displaying ID from email
                id.setText(account.getEmail());

                //Storing Name into Cred Class
                sName = account.getDisplayName();

                String[] n_split = sName.split(" ");

                char firstch = n_split[0].charAt(0);
                char secondch = n_split[0].charAt(1);
                if((firstch >= '0' && firstch <= '9') || (secondch>='0' && secondch <='9')){
                    String combinedName = n_split[1];
                    for(int i = 2; i<n_split.length; i++){
                        combinedName = combinedName + " "+ n_split[i];
                    }

                    //Storing and displaying the user name
                    Creds.sName= combinedName;
                    name.setText(combinedName);

                }
                else {
                    //Storing the user name and displaying it
                    name.setText(account.getDisplayName());
                    Creds.sName = sName;
                }

                Toast.makeText(StudentMainPage.this,"You are Logged In", Toast.LENGTH_SHORT).show();

                //onClick Listeners will only work if valid charusat email is typed in

                card_form.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent form=new Intent(StudentMainPage.this, Achievement_Form.class);
                        startActivity(form);
                    }
                });

                card_submitted.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent recycler = new Intent(StudentMainPage.this, RecyclerViewStudentSubmitted.class);
                        startActivity(recycler);
                    }
                });


            }
            // if email is not of charusat then app shows an error
            else{
                Toast.makeText(StudentMainPage.this,"Please Use Charusat Email", Toast.LENGTH_SHORT).show();
                name.setText("ERROR");
                id.setText("LOG OUT TO ACCESS DIFFERENT EMAIL");

            }
        }
        else {
            gotoMainActivity();
        }
    }

}
