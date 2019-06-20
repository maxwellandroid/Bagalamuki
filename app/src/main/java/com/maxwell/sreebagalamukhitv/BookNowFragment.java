package com.maxwell.sreebagalamukhitv;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BookNowFragment extends Fragment {

    View view;

    TextView tv_astro_prediction_booking,tv_amavasya_yaagam_pbooking,tv_pournami_yaagam_booking,tv_thila_homam_booking,tv_ganapathi_homam_booking;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.layout_book_now, container, false);

        tv_astro_prediction_booking=(TextView)view.findViewById(R.id.text_astrological_prediction_booking);
        tv_amavasya_yaagam_pbooking=(TextView)view.findViewById(R.id.text_amavasya_yagam_booking);
        tv_pournami_yaagam_booking=(TextView)view.findViewById(R.id.text_pournami_yaagam_booking);
        tv_thila_homam_booking=(TextView)view.findViewById(R.id.text_thila_homam_booking);
        tv_ganapathi_homam_booking=(TextView)view.findViewById(R.id.text_ganapathi_homam_booking);

        tv_astro_prediction_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getActivity(),AstrologicalPredictionsBookingActivity.class);
                startActivity(i);
            }
        });

        tv_amavasya_yaagam_pbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String action;
                Intent i=new Intent(getActivity(),AmavaasaiYaagamBookingActivity.class);
                startActivity(i);
            }
        });
        tv_pournami_yaagam_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String action;
                Intent i=new Intent(getActivity(),PournamiYaagamActivity.class);
                startActivity(i);
            }
        });
        tv_thila_homam_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getActivity(),ThilaHomamActivity.class);
                startActivity(i);
            }
        });

        tv_ganapathi_homam_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(getActivity(),GanapathyHomamActivity.class);
                startActivity(i);
            }
        });
        return view;

    }
}
