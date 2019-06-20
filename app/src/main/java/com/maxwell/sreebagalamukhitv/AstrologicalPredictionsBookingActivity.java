package com.maxwell.sreebagalamukhitv;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AstrologicalPredictionsBookingActivity extends AppCompatActivity {

    RadioGroup rg_gender,rg_marital;
    RadioButton rb_male,rb_female,rb_married_no,rb_married_yes;

    LinearLayout layout_spouse_detaials;
    EditText et_amount,et_name,et_email,et_mobile_number,et_address,et_dob,et_tob,et_pob,et_district,et_country,et_raasi,et_birth_star,et_spouse_name,et_spouse_dob,et_spouse_tob,et_spouse_pob,et_spouse_district,et_spouse_country,et_spouse_rasi,et_spouse_birth_star,et_any_specific_question;

    String s_amount,s_name,s_email,s_mobile_numaber,s_address,s_dob,s_tob,s_pob,s_district,s_country,s_raasi,s_birth_star,s_spouse_name,s_spouse_dob,s_spouse_tob,s_spouse_pob,s_spouse_district,s_spouse_country,s_spouse_rasi,s_spouse_birthstar,s_specific_question;

    String s_gender="",s_married="";

    Button button_book_now;

    List<String> rasi=new ArrayList<>();
    List<String> nakchtram=new ArrayList<>();

    DatePickerDialog datePickerDialog,datePickerDialog1;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar,calendar1;
    ListPopupWindow rasiPopup,natchathiramPopup,spouseRasiPopup,spouseNatchathiramPopup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astrological_predictions_booking);

        initializeViews();
    }

    public void backPressed(View view){
        onBackPressed();
    }

    public void initializeViews(){

        et_amount=(EditText)findViewById(R.id.edittext_amount);
        et_name=(EditText)findViewById(R.id.edittext_name);
        et_email=(EditText)findViewById(R.id.edittext_email);
        et_mobile_number=(EditText)findViewById(R.id.edittext_mobile_number);
        et_address=(EditText)findViewById(R.id.edittext_address);
        et_dob=(EditText)findViewById(R.id.edittext_dob);
        et_tob=(EditText)findViewById(R.id.edittext_tob);
        et_pob=(EditText)findViewById(R.id.edittext_pob);
        et_district=(EditText)findViewById(R.id.edittext_district);
        et_country=(EditText)findViewById(R.id.edittext_country);
        et_raasi=(EditText)findViewById(R.id.edittext_rasi);
        et_birth_star=(EditText)findViewById(R.id.edittext_nakshtram);
        et_spouse_name=(EditText)findViewById(R.id.edittext_spouse_name);
        et_spouse_dob=(EditText)findViewById(R.id.edittext_spouse_dob);
        et_spouse_tob=(EditText)findViewById(R.id.edittext_spouse_tob);
        et_spouse_pob=(EditText)findViewById(R.id.edittext_spouse_pob);
        et_spouse_district=(EditText)findViewById(R.id.edittext_spouse_district);
        et_spouse_country=(EditText)findViewById(R.id.edittext_spouse_country);
        et_spouse_rasi=(EditText)findViewById(R.id.edittext_spouse_rasi);
        et_spouse_birth_star=(EditText)findViewById(R.id.edittext_spouse_nakchatram);
        et_any_specific_question=(EditText)findViewById(R.id.edittext_anyspecific_question);
        button_book_now=(Button)findViewById(R.id.button_book_now);

        rb_male=(RadioButton)findViewById(R.id.radio_male);
        rb_female=(RadioButton)findViewById(R.id.radio_female);
        rb_married_no=(RadioButton)findViewById(R.id.radio_married_no);
        rb_married_yes=(RadioButton)findViewById(R.id.radio_married_yes);

        layout_spouse_detaials =(LinearLayout)findViewById(R.id.layout_spouse_details);

        rasiPopup=new ListPopupWindow(getApplicationContext());
        natchathiramPopup=new ListPopupWindow(getApplicationContext());
        spouseRasiPopup=new ListPopupWindow(getApplicationContext());
        spouseNatchathiramPopup=new ListPopupWindow(getApplicationContext());

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        et_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(AstrologicalPredictionsBookingActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int selectedyear, int selectedmonth, int selectedDay) {

                                //  date.setText(day + "/" + (month + 1) + "/" + year);
                                s_dob=String.valueOf(selectedDay)+"-"+String.valueOf(selectedmonth+1)+"-"+String.valueOf(selectedyear);
                                year=selectedyear;
                                month=selectedmonth;
                                dayOfMonth=selectedDay;
                                et_dob.setText(s_dob);
                                // datePickerDialog.dismiss();


                            }


                        }, year, month, dayOfMonth);
                // datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-20000);

                datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {

                        dialog.dismiss();
                    }
                });

               /* calendar.set(year-18,month,dayOfMonth);
                long value=calendar.getTimeInMillis();*/
               // calendar1=Calendar.getInstance();
               /* calendar1.set(calendar1.get(Calendar.YEAR)-18,calendar1.get(Calendar.MONTH),calendar1.get(Calendar.DAY_OF_MONTH));
                long value=calendar1.getTimeInMillis();
                datePickerDialog.getDatePicker().setMaxDate(value);*/
                   datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
                //datePickerDialog.getDatePicker().setMinDate(new Date().getTime()-6570);
                datePickerDialog.show();


            }
        });
        et_spouse_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog1 = new DatePickerDialog(AstrologicalPredictionsBookingActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int selectedyear, int selectedmonth, int selectedDay) {

                                //  date.setText(day + "/" + (month + 1) + "/" + year);
                                s_spouse_dob=String.valueOf(selectedDay)+"-"+String.valueOf(selectedmonth+1)+"-"+String.valueOf(selectedyear);
                                year=selectedyear;
                                month=selectedmonth;
                                dayOfMonth=selectedDay;
                                et_spouse_dob.setText(s_spouse_dob);
                                // datePickerDialog.dismiss();


                            }


                        }, year, month, dayOfMonth);
                // datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-20000);

                datePickerDialog1.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {

                        dialog.dismiss();
                    }
                });

               /* calendar.set(year-18,month,dayOfMonth);
                long value=calendar.getTimeInMillis();*/
               // calendar1=Calendar.getInstance();
               /* calendar1.set(calendar1.get(Calendar.YEAR)-18,calendar1.get(Calendar.MONTH),calendar1.get(Calendar.DAY_OF_MONTH));
                long value=calendar1.getTimeInMillis();
                datePickerDialog.getDatePicker().setMaxDate(value);*/
                   datePickerDialog1.getDatePicker().setMaxDate(new Date().getTime());
                //datePickerDialog.getDatePicker().setMinDate(new Date().getTime()-6570);
                datePickerDialog1.show();


            }
        });

        rb_married_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(rb_married_yes.isChecked()){
                    layout_spouse_detaials.setVisibility(View.VISIBLE);
                    s_married="yes";
                }else {
                    layout_spouse_detaials.setVisibility(View.GONE);
                }
            }
        });

        rb_married_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(rb_married_no.isChecked()){
                    s_married="no";
                    layout_spouse_detaials.setVisibility(View.GONE);
                }
            }
        });


        rb_male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    s_gender="male";
                }
            }
        });
        rb_female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    s_gender="female";
                }
            }
        });

        rasi.clear();
        rasi.add("-Select-");
        rasi.add("Mesha-Aries");
        rasi.add("Rishabha-Taurus");
        rasi.add("Mithuna-Aries");
        rasi.add("Kark-Cancer");
        rasi.add("Simha-Lio");
        rasi.add("Kanya-Virgo");
        rasi.add("Thula-Libra");
        rasi.add("Vrishchika-Scorpio");
        rasi.add("Dhanu-Sagittarius");
        rasi.add("Makara-Capricorn");
        rasi.add("Kumbha-Aquarius");
        rasi.add("Meena-Pisces");

        nakchtram.clear();
        nakchtram.add("-Select-");
        nakchtram.add("Aswini ( Aswathy )");
        nakchtram.add("Bharani");
        nakchtram.add("Krithika");
        nakchtram.add("Rohini");
        nakchtram.add("Mrigasiram");
        nakchtram.add("Thiruvadhirai (Arudra)");
        nakchtram.add("Punarpoosam (Punarvasu)");
        nakchtram.add("Poosam (Pushyami)");
        nakchtram.add("Ayilyam (Aslesha)");
        nakchtram.add("Magam (Makha)");
        nakchtram.add("Pooram (PoorvaPhalguni)");
        nakchtram.add("Uthiram (UthraPalguni)");
        nakchtram.add("Hastham");
        nakchtram.add("Chithirai ( Chithra )");
        nakchtram.add("Swathi");
        nakchtram.add("Visagam ( Vishaka )");
        nakchtram.add("Anusham ( Anuradha )");
        nakchtram.add("Kettai ( jyeshta )");
        nakchtram.add("Moolam");
        nakchtram.add("Pooradam ( Poorvashada )");
        nakchtram.add("Uthiradam (Uthrashada)");
        nakchtram.add("Thiruvonam");
        nakchtram.add("Avittam ( Dhanishta )");
        nakchtram.add("Sadhayam ( Sathabhisha )");
        nakchtram.add("Poorattadhi ( Poorvabhadra )");
        nakchtram.add("Uthirattadhi ( Uthrabhadra )");
        nakchtram.add("Revathi");

        rasiPopup.setAdapter(new ArrayAdapter(
                getApplicationContext(),
                R.layout.layout_pop_up_row, rasi));
        rasiPopup.setAnchorView(et_raasi);
        rasiPopup.setModal(true);

        spouseRasiPopup.setAdapter(new ArrayAdapter(
                getApplicationContext(),
                R.layout.layout_pop_up_row, rasi));
        spouseRasiPopup.setAnchorView(et_spouse_rasi);
        spouseRasiPopup.setModal(true);

        natchathiramPopup.setAdapter(new ArrayAdapter(
                getApplicationContext(),
                R.layout.layout_pop_up_row, nakchtram));
        natchathiramPopup.setAnchorView(et_birth_star);
        natchathiramPopup.setModal(true);

        spouseNatchathiramPopup.setAdapter(new ArrayAdapter(
                getApplicationContext(),
                R.layout.layout_pop_up_row, nakchtram));
        spouseNatchathiramPopup.setAnchorView(et_spouse_birth_star);
        spouseNatchathiramPopup.setModal(true);


        rasiPopup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                et_raasi.setText(rasi.get(position));
                rasiPopup.dismiss();
            }
        });
        natchathiramPopup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                et_birth_star.setText(nakchtram.get(position));
                natchathiramPopup.dismiss();
            }
        });
  spouseRasiPopup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                et_spouse_rasi.setText(rasi.get(position));
                spouseRasiPopup.dismiss();
            }
        });
        spouseNatchathiramPopup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                et_spouse_birth_star.setText(nakchtram.get(position));
                spouseNatchathiramPopup.dismiss();
            }
        });
        et_raasi.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                rasiPopup.show();
                return false;
            }
        });
        et_spouse_rasi.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                spouseRasiPopup.show();
                return false;
            }
        });

        et_birth_star.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                natchathiramPopup.show();
                return false;
            }
        });
        et_spouse_birth_star.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                spouseNatchathiramPopup.show();
                return false;
            }
        });


        button_book_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                s_name=et_name.getText().toString().trim();
                s_email=et_email.getText().toString().trim();
                s_mobile_numaber=et_mobile_number.getText().toString().trim();
                s_address=et_address.getText().toString().trim();
                s_dob=et_dob.getText().toString().trim();
                s_tob=et_tob.getText().toString().trim();
                s_pob=et_pob.getText().toString().trim();
                s_district=et_district.getText().toString().trim();
                s_country=et_country.getText().toString().trim();
                s_raasi=et_raasi.getText().toString().trim();
                s_birth_star=et_birth_star.getText().toString().trim();
                s_spouse_name=et_spouse_name.getText().toString().trim();
                s_spouse_dob=et_spouse_dob.getText().toString().trim();
                s_spouse_tob=et_spouse_tob.getText().toString().trim();
                s_spouse_pob=et_spouse_pob.getText().toString().trim();
                s_spouse_district=et_spouse_district.getText().toString().trim();
                s_spouse_country=et_spouse_country.getText().toString().trim();
                s_spouse_rasi=et_spouse_rasi.getText().toString().trim();
                s_spouse_birthstar=et_spouse_birth_star.getText().toString().trim();
                s_specific_question=et_any_specific_question.getText().toString().trim();

                bookOnlinePrediction();


            }
        });


    }

    public void bookOnlinePrediction(){

        RequestQueue requestQueue = Volley.newRequestQueue(AstrologicalPredictionsBookingActivity.this);
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, StringConstants.mainUrl + StringConstants.bookAstrologicalPredictionUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                Log.d("Response",response);
                try {
                    JSONObject jsonObject=new JSONObject(response.trim());


                    if (jsonObject.has("response")) {
                        String response1 = jsonObject.getString("response");

                        if(response1.matches("success")){

                            showAlertDialog("Your booking is submitted successfully.");
                        }


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {

                String errormessage=StringConstants.ErrorMessage(error);
                //showAlertDialog(errormessage);
                //This code is executed if there is an error.
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put(StringConstants.inputPredictionType, "Online");
                MyData.put(StringConstants.inputAmount, "500");
                MyData.put(StringConstants.inputName, s_name);
                MyData.put(StringConstants.inputEmailID, s_email);
                MyData.put(StringConstants.inputPhone, s_mobile_numaber);
                MyData.put(StringConstants.inputAddress, s_address);
                MyData.put(StringConstants.inputgender, s_gender);
                MyData.put(StringConstants.inputDOB, s_dob);
                MyData.put(StringConstants.inputTOB, s_tob);
                MyData.put(StringConstants.inputPOB, s_pob);
                MyData.put(StringConstants.inputDistrict, s_district);
                MyData.put(StringConstants.inputCountry, s_country);
                MyData.put(StringConstants.inputRaasi, s_raasi);
                MyData.put(StringConstants.inputStar, s_birth_star);
                MyData.put(StringConstants.inputMarried, s_married);
                MyData.put(StringConstants.inputSpouseName, s_spouse_name);
                MyData.put(StringConstants.inputSpouseDOB, s_spouse_dob);
                MyData.put(StringConstants.inputSpouseTOB, s_spouse_tob);
                MyData.put(StringConstants.inputSpousePOB, s_spouse_pob);
                MyData.put(StringConstants.inputSpouseDistrict, s_spouse_district);
                MyData.put(StringConstants.inputSpouseCountry, s_spouse_country);
                MyData.put(StringConstants.inputSpouseRaasi, s_spouse_rasi);
                MyData.put(StringConstants.inputSpouseStar, s_spouse_birthstar);
                MyData.put(StringConstants.inputQuestion, s_specific_question);


                return MyData;
            }
        };

        requestQueue.add(MyStringRequest);

    }
    public void showAlertDialog(String message){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AstrologicalPredictionsBookingActivity.this);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton("ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        arg0.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


}
