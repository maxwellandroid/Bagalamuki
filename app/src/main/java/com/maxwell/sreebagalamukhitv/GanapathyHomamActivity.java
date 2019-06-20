package com.maxwell.sreebagalamukhitv;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GanapathyHomamActivity extends AppCompatActivity {

    RadioButton rb_star_rasi,rb_dont_know_rasi;
    LinearLayout layout_star_rasi,layout_dont_know_rasi;

    EditText et_name,et_email_id,et_mobile_number,et_no_of_persons,et_amount,et_total_amount,et_rasi,et_nakchatram,et_dob,et_tob,et_pob,et_address,et_purpose,et_message;
    String s_name,s_email,s_mobile_number,s_no_of_persons,s_amount,s_total_amount,s_rasi,s_natchathiram,s_dob,s_tob,s_pod,s_address,s_purpose,s_message;
    Button button;

    String s_star_rasi;

    List<String> rasi=new ArrayList<>();
    List<String> nakchtram=new ArrayList<>();
    List<String> noOfPersons=new ArrayList<>();
    List<String> purpose=new ArrayList<>();

    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    ListPopupWindow rasiPopup,natchathiramPopup,noOfPersonsPopup,yaagamPurposePopup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganapathy_homam);
        rb_star_rasi=(RadioButton)findViewById(R.id.radioButton_star_rasi);
        rb_dont_know_rasi=(RadioButton)findViewById(R.id.radioButton_dont_know_rasi);
        layout_star_rasi=(LinearLayout)findViewById(R.id.layout_rai);
        layout_dont_know_rasi=(LinearLayout)findViewById(R.id.layout_dont_know_rasi);

        et_name=(EditText)findViewById(R.id.edittext_name);
        et_email_id=(EditText)findViewById(R.id.edittext_email);
        et_mobile_number=(EditText)findViewById(R.id.edittext_mobile_number);
        et_no_of_persons=(EditText)findViewById(R.id.edittext_no_of_persons);
        et_amount=(EditText)findViewById(R.id.edittext_amount);
        et_total_amount=(EditText)findViewById(R.id.edittext_total_amount);
        et_rasi=(EditText)findViewById(R.id.edittext_rasi);
        et_nakchatram=(EditText)findViewById(R.id.edittext_nakshtram);
        et_dob=(EditText)findViewById(R.id.edittext_dob);
        et_tob=(EditText)findViewById(R.id.edittext_tob);
        et_pob=(EditText)findViewById(R.id.edittext_pob);
        et_address=(EditText)findViewById(R.id.edittext_address);
        et_purpose=(EditText)findViewById(R.id.edittext_purpose_of_yaagam);
        et_message=(EditText)findViewById(R.id.edittext_message);

        button=(Button)findViewById(R.id.button_book_now);

        rasiPopup=new ListPopupWindow(getApplicationContext());
        natchathiramPopup=new ListPopupWindow(getApplicationContext());
        noOfPersonsPopup=new ListPopupWindow(getApplicationContext());
        yaagamPurposePopup=new ListPopupWindow(getApplicationContext());
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        et_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(GanapathyHomamActivity.this,
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
        et_rasi.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                rasiPopup.show();
                return false;
            }
        });

        et_nakchatram.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                natchathiramPopup.show();
                return false;
            }
        });


        rb_star_rasi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    layout_star_rasi.setVisibility(View.VISIBLE);
                    layout_dont_know_rasi.setVisibility(View.GONE);
                }
            }
        });
        rb_dont_know_rasi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    layout_star_rasi.setVisibility(View.GONE);
                    layout_dont_know_rasi.setVisibility(View.VISIBLE);
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

        noOfPersons.clear();
        noOfPersons.add("- Select -");
        noOfPersons.add("1");
        noOfPersons.add("2");
        noOfPersons.add("3");
        noOfPersons.add("4");
        noOfPersons.add("5");
        noOfPersons.add("6");
        noOfPersons.add("7");
        noOfPersons.add("8");
        noOfPersons.add("9");
        noOfPersons.add("10");
        noOfPersons.add("11");
        noOfPersons.add("12");

        purpose.clear();

        purpose.add("- Select -");
        purpose.add("Court case");
        purpose.add("Sathru Samharam");
        purpose.add("Black Magic");
        purpose.add("Health Problem");
        purpose.add("Others");

        rasiPopup.setAdapter(new ArrayAdapter(
                getApplicationContext(),
                R.layout.layout_pop_up_row, rasi));
        rasiPopup.setAnchorView(et_rasi);
        rasiPopup.setModal(true);

        natchathiramPopup.setAdapter(new ArrayAdapter(
                getApplicationContext(),
                R.layout.layout_pop_up_row, nakchtram));
        natchathiramPopup.setAnchorView(et_nakchatram);
        natchathiramPopup.setModal(true);
        noOfPersonsPopup.setAdapter(new ArrayAdapter(
                getApplicationContext(),
                R.layout.layout_pop_up_row, noOfPersons));
        noOfPersonsPopup.setAnchorView(et_no_of_persons);
        noOfPersonsPopup.setModal(true);

        yaagamPurposePopup.setAdapter(new ArrayAdapter(
                getApplicationContext(),
                R.layout.layout_pop_up_row, purpose));
        yaagamPurposePopup.setAnchorView(et_purpose);
        yaagamPurposePopup.setModal(true);

        et_no_of_persons.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                noOfPersonsPopup.show();
                return false;
            }
        });

        et_purpose.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                yaagamPurposePopup.show();
                return false;
            }
        });

        noOfPersonsPopup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                et_no_of_persons.setText(noOfPersons.get(position));

                if(position>0){
                    int amount= Integer.parseInt(noOfPersons.get(position))*500;
                    et_amount.setText(String.valueOf(amount));

                }
                noOfPersonsPopup.dismiss();
            }
        });
        yaagamPurposePopup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                et_purpose.setText(purpose.get(position));
                yaagamPurposePopup.dismiss();
            }
        });

        rasiPopup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                et_rasi.setText(rasi.get(position));
                rasiPopup.dismiss();
            }
        });
        natchathiramPopup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                et_nakchatram.setText(nakchtram.get(position));
                natchathiramPopup.dismiss();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                s_name=et_name.getText().toString().trim();
                s_email=et_email_id.getText().toString().trim();
                s_mobile_number=et_mobile_number.getText().toString().trim();
                s_no_of_persons=et_no_of_persons.getText().toString().trim();
                s_amount=et_amount.getText().toString().trim();
                s_total_amount=et_total_amount.getText().toString().trim();
                s_rasi=et_rasi.getText().toString().trim();
                s_natchathiram=et_nakchatram.getText().toString().trim();
                s_dob=et_dob.getText().toString().trim();
                s_tob=et_tob.getText().toString().trim();
                s_pod=et_pob.getText().toString().trim();
                s_address=et_address.getText().toString().trim();

                if(!s_star_rasi.isEmpty()){
                    if(s_star_rasi.equals("Star/Rasi")){
                     //   bookAmavasyaYaagam();
                    }else {
                       // bookAmavasyaYaagam1();
                    }
                }
            }
        });

    }
    public void backPressed(View view){
        onBackPressed();
    }

}
