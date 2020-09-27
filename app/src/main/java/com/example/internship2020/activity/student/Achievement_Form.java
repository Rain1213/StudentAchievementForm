package com.example.internship2020.activity.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.internship2020.R;
import com.example.internship2020.api.ApiClient;
import com.example.internship2020.api.ApiInterface;
import com.example.internship2020.model.Creds;
import com.example.internship2020.model.Form;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Achievement_Form extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, FormView {

    Spinner spinner_activity;

    EditText edt_id;
    EditText edt_name;
    EditText edt_sem;
    EditText edt_event_name;
    EditText edt_date_from;
    EditText edt_date_to;
    EditText edt_scholarship_charusat;
    EditText edt_scholarship_external;
    EditText edt_certificate;
    Button btn_submit;

    DatePickerDialog datePickerDialog;

    ProgressDialog progressDialog;

    ApiInterface apiInterface;

    FormPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement__form);

        spinner_activity = findViewById(R.id.spinner_activity);

        edt_id=findViewById(R.id.edt_id);
        edt_name=findViewById(R.id.edt_name);
        edt_sem = findViewById(R.id.edt_sem);
        edt_event_name=findViewById(R.id.edt_event_name);
        edt_date_from = findViewById(R.id.edt_date_from);
        edt_date_to = findViewById(R.id.edt_date_to);
        edt_scholarship_charusat = findViewById(R.id.edt_scholarship_charusat);
        edt_scholarship_external = findViewById(R.id.edt_scholarship_external);
        edt_certificate=findViewById(R.id.edt_certificate);
        btn_submit=findViewById(R.id.btn_submit);


        //Progress Dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait");

        //Pre-storing ID and Name values in form
        edt_id.setText(""+ Creds.sId.toUpperCase());
        edt_name.setText(""+Creds.sName);

        presenter = new FormPresenter(this);




        ArrayList<String> activity= new ArrayList<String>();
        activity.add("Choose");
        activity.add("Non Technical Event");
        activity.add("Technical Event");
        activity.add("Academic Certifications");
        activity.add("Internship");
        activity.add("Others");

        ArrayAdapter<String> adapter_activity = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, activity){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }

            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        adapter_activity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_activity.setAdapter(adapter_activity);


        Calendar c = Calendar.getInstance();
        final int cYear = c.get(Calendar.YEAR);
        final int cMonth = c.get(Calendar.MONTH);
        final int cDay = c.get(Calendar.DAY_OF_MONTH);

        edt_date_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(Achievement_Form.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edt_date_from.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                },cYear,cMonth,cDay);
                datePickerDialog.show();
            }
        });


        edt_date_to.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                datePickerDialog= new DatePickerDialog(Achievement_Form.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edt_date_to.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                },cYear,cMonth,cDay);

                datePickerDialog.show();

            }
        });

        
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //ERROR MESSAGE HERE

                if(edt_id.getText().toString().isEmpty()){
                    edt_id.setError("Please fill in the ID");
                    edt_id.requestFocus();
                }
                else if(edt_name.getText().toString().isEmpty()){
                    edt_name.setError("Please fill in your Name");
                    edt_name.requestFocus();
                }
                else if(edt_sem.getText().toString().isEmpty()){
                    edt_sem.setError("Enter your Current Semester");
                    edt_sem.requestFocus();
                }
                else if(spinner_activity.getSelectedItem()== "Choose"){
                    Toast.makeText(Achievement_Form.this,
                            "Please Select the Event from Dropdown Menu",
                            Toast.LENGTH_SHORT).show();

                }
                else if(edt_event_name.getText().toString().isEmpty()){
                    edt_event_name.setError("Please fill in the Event Name");
                    edt_event_name.requestFocus();
                }
                else if(edt_date_from.getText().toString().matches("DD/MM/YY")){
                    edt_date_from.setError("Date Missing");
                    edt_date_from.requestFocus();
                }
                else if(edt_date_to.getText().toString().matches("DD/MM/YY")){
                    edt_date_to.setError("Date Missing");
                    edt_date_to.requestFocus();
                }
                else if(edt_scholarship_charusat.getText().toString().matches("")){
                    edt_scholarship_charusat.setError("Enter 0 if no amount allocated");
                    edt_scholarship_charusat.requestFocus();
                }
                else if(edt_scholarship_external.getText().toString().matches("")){
                    edt_scholarship_external.setError("Enter 0 if no amount allocated");
                    edt_scholarship_external.requestFocus();
                }
                else if (edt_certificate.getText().toString().isEmpty()){
                    edt_certificate.setError("Make Sure The Link is Proper");
                    edt_certificate.requestFocus();
                }
                else {

                    //Initializing only after the values are inserted correctly
                    String StdID = edt_id.getText().toString().trim();
                    String StdName = edt_name.getText().toString().trim();
                    int StdSem = Integer.parseInt(edt_sem.getText().toString().trim());

                    //Selecting EventType
                    String EventType = spinner_activity.getSelectedItem().toString().trim();

                    String EventName = edt_event_name.getText().toString().trim();
                    String FromDate = edt_date_from.getText().toString().trim();
                    String ToDate = edt_date_to.getText().toString().trim();
                    int ClgSch = Integer.parseInt(edt_scholarship_charusat.getText().toString().trim());
                    int ExtSch = Integer.parseInt(edt_scholarship_external.getText().toString().trim());
                    String Drive = edt_certificate.getText().toString().trim();
                    // 0 means admin has neither accepted nor rejected
                    int Accept = 0;

                    presenter.saveData(StdID, StdName, StdSem, EventType, EventName, FromDate, ToDate, ClgSch, ExtSch, Drive, Accept);
                }
            }
        });


    }



    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

    }


    @Override
    public void showProgress() {
        progressDialog.show();

    }

    @Override
    public void hideProgress() {
        progressDialog.hide();

    }

    @Override
    public void onRequestSuccess(String message) {
        Toast.makeText(Achievement_Form.this,
                message,
                Toast.LENGTH_SHORT).show();
        finish(); //back to Student Main Activity

    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(Achievement_Form.this,
                message,
                Toast.LENGTH_SHORT).show();

    }
}
