package com.maxwell.sreebagalamukhitv;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LanguageselectionFragment extends Fragment {

    View view;
    TextView tv_tamil,tv_english,tv_hindi,tv_telugu,tv_malayalam;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.layout_language_selection,container,false);
        tv_tamil=(TextView)view.findViewById(R.id.text_tamil);
        tv_english=(TextView)view.findViewById(R.id.text_english);
        tv_hindi=(TextView)view.findViewById(R.id.text_hindi);
        tv_telugu=(TextView)view.findViewById(R.id.text_telugu);
        tv_malayalam=(TextView)view.findViewById(R.id.text_malayalam);

        tv_tamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),VideosActivity.class);
                i.putExtra("Language","Tamil");
                startActivity(i);
            }
        });
        tv_telugu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),VideosActivity.class);
                i.putExtra("Language","Telugu");
                startActivity(i);
            }
        });
        tv_english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),VideosActivity.class);
                i.putExtra("Language","English");
                startActivity(i);
            }
        });
        tv_hindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),VideosActivity.class);
                i.putExtra("Language","Hindi");
                startActivity(i);
            }
        });
        tv_malayalam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),VideosActivity.class);
                i.putExtra("Language","Malayalam");
                startActivity(i);
            }
        });
        return view;
    }
}
