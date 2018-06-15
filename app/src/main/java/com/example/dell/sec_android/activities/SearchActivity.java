package com.example.dell.sec_android.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.dell.sec_android.R;
import com.example.dell.sec_android.adapters.RecyclerViewAdapter;
import com.example.dell.sec_android.pojos.ZillaParishad;
import com.example.dell.sec_android.utils.SpinnerArrayListPopulate;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";
    @BindView(R.id.recyclerView)RecyclerView recyclerView;
    RecyclerViewAdapter mAdapter;
    ArrayList<ZillaParishad> searchList;
    ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        Intent i = getIntent();
        String divisionSpinner =  i.getStringExtra("divisionSpinner");
        String districtSpinner = i.getStringExtra("districtSpinner");
        String localBodySpinner = i.getStringExtra("localBodySpinner");
        String epSpinner = i.getStringExtra("epSpinner");
        String edSpinner = i.getStringExtra("edSpinner");
        dialog = new ProgressDialog(SearchActivity.this);
        dialog.setMessage("Loading");

        SpinnerArrayListPopulate spinnerArrayListPopulate = new SpinnerArrayListPopulate(new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>());
        spinnerArrayListPopulate.setListener(new SpinnerArrayListPopulate.SpinnerArrayListPopulateListener() {
            @Override
            public void onZPListPopulated(ArrayList<String> list) {

            }

            @Override
            public void onZPList1Populated(ArrayList<ZillaParishad> list) {
                searchList = list;
                if (dialog != null)
                    dialog.dismiss();
                Log.d(TAG, "onListPopulated: " + searchList.toString());
                mAdapter = new RecyclerViewAdapter(searchList,getApplication());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);
            }

        });

        spinnerArrayListPopulate.getSearchList(districtSpinner,divisionSpinner,localBodySpinner,epSpinner,edSpinner);
        dialog.show();




    }
}
