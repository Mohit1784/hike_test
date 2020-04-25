package com.android.hike.stimulation.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.android.hike.stimulation.AppConstants;
import com.android.hike.stimulation.R;

public class HomeSearch extends AppCompatActivity {

    AutoCompleteTextView SearchView;
    Button Search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_search);
        SearchView = findViewById(R.id.searchview);
        Search = findViewById(R.id.Searchbtn);
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SearchView.length()>0) {
                    StartGallaryActvity();

                }
            }
        });
    }

    private void StartGallaryActvity()
    {
        Intent searchintent = new Intent(this,PhotoGallery.class);
        searchintent.putExtra(AppConstants.QUERY_STRING,SearchView.getText().toString());
        startActivity(searchintent);
    }
}
