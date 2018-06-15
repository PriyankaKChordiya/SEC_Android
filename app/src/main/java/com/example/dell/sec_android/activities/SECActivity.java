package com.example.dell.sec_android.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.sec_android.R;
import com.example.dell.sec_android.pojos.ZillaParishad;
import com.example.dell.sec_android.utils.SpinnerArrayListPopulate;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dell on 13/6/18.
 */

public class SECActivity extends AppCompatActivity {

    public static String TAG = "SECActivity";
    @BindView(R.id.localBodyTV)
    TextView localBodyTV;
    @BindView(R.id.divisionTV)
    TextView divisionTV;
    @BindView(R.id.districtTv)
    TextView districtTV;
    @BindView(R.id.talukaTV)
    TextView talukaTV;
    @BindView(R.id.villageNameTV)
    TextView villageNameTV;
    @BindView(R.id.wardTV)
    TextView wardTV;
    @BindView(R.id.localBodySpinner)
    Spinner localBodySpinner;
    @BindView(R.id.divisionSpinner)
    Spinner divisionSpinner;
    @BindView(R.id.districtSpinner)
    Spinner districtSpinner;
    @BindView(R.id.talukaSpinner)
    Spinner talukaSpinner;
    @BindView(R.id.villageNameSpinner)
    Spinner villageNameSpinner;
    @BindView(R.id.wardSpinner)
    Spinner wardSpinner;
    @BindView(R.id.searchBtn)
    Button searchBtn;

    ProgressDialog dialog;
    ArrayList<String> lbList, divList, distList, epList, edList;
    ArrayAdapter localBodyAdapter, divisionAdapter, districtAdapter, epAdapter, edAdapter;
    ArrayList<ZillaParishad> ZPlist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
        ButterKnife.bind(this);

        lbList = new ArrayList<>();
        lbList.add("Gram Panchayat");
        lbList.add("Zilla Parishad");
        lbList.add("Panchayat Samiti");
        lbList.add("Muncipal Council");
        lbList.add("Muncipal Corporation");
        lbList.add("Nagar Panchayat");

        localBodyAdapter = new ArrayAdapter(SECActivity.this, android.R.layout.simple_spinner_item, lbList);
        localBodySpinner.setAdapter(localBodyAdapter);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edList != null) {
                    Intent intent = new Intent(SECActivity.this, SearchActivity.class);
                    intent.putExtra("divisionSpinner", divisionSpinner.getSelectedItem().toString());
                    intent.putExtra("districtSpinner", districtSpinner.getSelectedItem().toString());
                    intent.putExtra("localBodySpinner", localBodySpinner.getSelectedItem().toString());
                    intent.putExtra("epSpinner", talukaSpinner.getSelectedItem().toString());
                    intent.putExtra("edSpinner", villageNameSpinner.getSelectedItem().toString());
                    startActivity(intent);
                }
                else{
                    Toast.makeText(SECActivity.this,"Please Select the above options...",Toast.LENGTH_LONG).show();
                }
            }
        });

        localBodySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                dialog = new ProgressDialog(SECActivity.this);
                dialog.setMessage("Loading");
                if (item == "Zilla Parishad") {
                    SpinnerArrayListPopulate spinnerArrayListPopulate = new SpinnerArrayListPopulate(new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>());
                    spinnerArrayListPopulate.setListener(new SpinnerArrayListPopulate.SpinnerArrayListPopulateListener() {
                        @Override
                        public void onZPListPopulated(ArrayList<String> list) {
                            divList = list;
                            if (dialog != null)
                                dialog.dismiss();
                            Log.d(TAG, "onListPopulated: " + divList.toString());
                            divisionAdapter = new ArrayAdapter(SECActivity.this, android.R.layout.simple_spinner_item, divList);
                            divisionSpinner.setAdapter(divisionAdapter);
                        }

                        @Override
                        public void onZPList1Populated(ArrayList<ZillaParishad> list) {

                        }
                    });

                    spinnerArrayListPopulate.getDivList();
                    dialog.show();
                    wardSpinner.setVisibility(View.INVISIBLE);
                    wardTV.setVisibility(View.INVISIBLE);
                    talukaTV.setText("Election Programme Name:");
                    villageNameTV.setText("Electoral Division Number:");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        divisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final String item = parent.getItemAtPosition(position).toString();
                dialog = new ProgressDialog(SECActivity.this);
                dialog.setMessage("Loading");
                SpinnerArrayListPopulate spinnerArrayListPopulate = new SpinnerArrayListPopulate(new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>());
                spinnerArrayListPopulate.setListener(new SpinnerArrayListPopulate.SpinnerArrayListPopulateListener() {
                    @Override
                    public void onZPListPopulated(ArrayList<String> list) {
                        distList = list;
                        if (dialog != null)
                            dialog.dismiss();
                        Log.d(TAG, "onListPopulated: " + distList.toString());
                        districtAdapter = new ArrayAdapter(SECActivity.this, android.R.layout.simple_spinner_item, distList);
                        districtSpinner.setAdapter(districtAdapter);
                    }

                    @Override
                    public void onZPList1Populated(ArrayList<ZillaParishad> list) {

                    }

                });

                spinnerArrayListPopulate.getDistList(item);
                dialog.show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                dialog = new ProgressDialog(SECActivity.this);
                dialog.setMessage("Loading");
                SpinnerArrayListPopulate spinnerArrayListPopulate = new SpinnerArrayListPopulate(new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>());
                spinnerArrayListPopulate.setListener(new SpinnerArrayListPopulate.SpinnerArrayListPopulateListener() {
                    @Override
                    public void onZPListPopulated(ArrayList<String> list) {
                        epList = list;
                        if (dialog != null)
                            dialog.dismiss();
                        Log.d(TAG, "onListPopulated: " + epList.toString());
                        epAdapter = new ArrayAdapter(SECActivity.this, android.R.layout.simple_spinner_item, epList);
                        talukaSpinner.setAdapter(epAdapter);
                    }

                    @Override
                    public void onZPList1Populated(ArrayList<ZillaParishad> list) {

                    }

                });

                spinnerArrayListPopulate.getEPList(item,divisionSpinner.getSelectedItem().toString(),localBodySpinner.getSelectedItem().toString());
                dialog.show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        talukaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                dialog = new ProgressDialog(SECActivity.this);
                dialog.setMessage("Loading");
                SpinnerArrayListPopulate spinnerArrayListPopulate = new SpinnerArrayListPopulate(new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>());
                spinnerArrayListPopulate.setListener(new SpinnerArrayListPopulate.SpinnerArrayListPopulateListener() {
                    @Override
                    public void onZPListPopulated(ArrayList<String> list) {
                        edList = list;
                        if (dialog != null)
                            dialog.dismiss();
                        Log.d(TAG, "onListPopulated: " + edList.toString());
                        edAdapter = new ArrayAdapter(SECActivity.this, android.R.layout.simple_spinner_item, edList);
                        villageNameSpinner.setAdapter(edAdapter);
                    }

                    @Override
                    public void onZPList1Populated(ArrayList<ZillaParishad> list) {

                    }

                });

                spinnerArrayListPopulate.getEDList(districtSpinner.getSelectedItem().toString(),divisionSpinner.getSelectedItem().toString(),localBodySpinner.getSelectedItem().toString(),item);
                dialog.show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}

