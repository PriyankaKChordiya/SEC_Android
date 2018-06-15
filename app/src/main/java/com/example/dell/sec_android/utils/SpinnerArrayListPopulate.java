package com.example.dell.sec_android.utils;

import com.example.dell.sec_android.pojos.ZillaParishad;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpinnerArrayListPopulate {
    private static final String TAG = "SpinnerAListPopulate";

    public interface SpinnerArrayListPopulateListener {
        public void onZPListPopulated(ArrayList<String> list);
        public void onZPList1Populated(ArrayList<ZillaParishad> list);
    }
    ArrayList<ZillaParishad> zpList;
    ArrayList<ZillaParishad> zpSearchList;
    ArrayList<String> mDivisionList;
    ArrayList<String> mDistrictList;
    ArrayList<String> mEPList;
    ArrayList<String> mEDList;
    SpinnerArrayListPopulateListener mListener;

    public SpinnerArrayListPopulateListener getListener() {
        return mListener;
    }

    public void setListener(SpinnerArrayListPopulateListener mListener) {
        this.mListener = mListener;
    }


    public SpinnerArrayListPopulate(ArrayList<String> divList,ArrayList<String> distList,ArrayList<String> epList,ArrayList<String> edList) {
        mDivisionList = divList;
        mDistrictList = distList;
        mEPList = epList;
        mEDList = edList;
    }

    public void getDivList() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<ZillaParishad>> stateListCall = apiInterface.getResult();
        stateListCall.enqueue(new Callback<List<ZillaParishad>>() {
            @Override
            public void onResponse(Call<List<ZillaParishad>> call, Response<List<ZillaParishad>> response) {
                List<ZillaParishad> list = response.body();
                zpList = new ArrayList<>();
                zpList.addAll(list);
                for(ZillaParishad zp: zpList){
                    mDivisionList.add(zp.getDivision());
                }

                // Remove duplicates from ArrayList of Strings.
                ArrayList<String> unique = removeDuplicates(mDivisionList);
                mDivisionList = unique;
                if (mListener != null)
                    mListener.onZPListPopulated(mDivisionList);
            }

            @Override
            public void onFailure(Call<List<ZillaParishad>> call, Throwable t) {

            }
        });
    }

    public void getDistList(final String division) {

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<ZillaParishad>> call = apiInterface.getResult();
        call.enqueue(new Callback<List<ZillaParishad>>() {
            @Override
            public void onResponse(Call<List<ZillaParishad>> call, Response<List<ZillaParishad>> response) {
                List<ZillaParishad> list = response.body();
                zpList = new ArrayList<>();
                zpList.addAll(list);
                for(ZillaParishad zp: zpList){
                    if(zp.getDivision().equals(division)) {
                        mDistrictList.add(zp.getDistrict());
                    }
                }

                // Remove duplicates from ArrayList of Strings.
                ArrayList<String> unique = removeDuplicates(mDistrictList);
                mDistrictList = unique;
                if (mListener != null)
                    mListener.onZPListPopulated(mDistrictList);
            }

            @Override
            public void onFailure(Call<List<ZillaParishad>> call, Throwable t) {

            }
        });
    }

    public void getEPList(final String district, final String division, String localbody) {

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<ZillaParishad>> call = apiInterface.getResult();
        call.enqueue(new Callback<List<ZillaParishad>>() {
            @Override
            public void onResponse(Call<List<ZillaParishad>> call, Response<List<ZillaParishad>> response) {
                List<ZillaParishad> list = response.body();
                zpList = new ArrayList<>();
                zpList.addAll(list);
                for(ZillaParishad zp: zpList){
                    if(zp.getDivision().equals(division) && zp.getDistrict().equals(district)) {
                        mEPList.add(zp.getElection_program_name());
                    }
                }

                // Remove duplicates from ArrayList of Strings.
                ArrayList<String> unique = removeDuplicates(mEPList);
                mEPList = unique;
                if (mListener != null)
                    mListener.onZPListPopulated(mEPList);
            }

            @Override
            public void onFailure(Call<List<ZillaParishad>> call, Throwable t) {

            }
        });
    }

    public void getEDList(final String district, final String division, String localbody,final String EP) {

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<ZillaParishad>> call = apiInterface.getResult();
        call.enqueue(new Callback<List<ZillaParishad>>() {
            @Override
            public void onResponse(Call<List<ZillaParishad>> call, Response<List<ZillaParishad>> response) {
                List<ZillaParishad> list = response.body();
                zpList = new ArrayList<>();
                zpList.addAll(list);
                for(ZillaParishad zp: zpList){
                    if(zp.getDivision().equals(division) && zp.getDistrict().equals(district) && zp.getElection_program_name().equals(EP)) {
                        mEDList.add(zp.getElectoral_division_num());
                    }
                }

                // Remove duplicates from ArrayList of Strings.
                ArrayList<String> unique = removeDuplicates(mEDList);
                mEDList = unique;
                if (mListener != null)
                    mListener.onZPListPopulated(mEDList);
            }

            @Override
            public void onFailure(Call<List<ZillaParishad>> call, Throwable t) {

            }
        });
    }


    public void getSearchList(final String district, final String division, String localbody,final String EP,final String ed) {

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<ZillaParishad>> call = apiInterface.getResult();
        call.enqueue(new Callback<List<ZillaParishad>>() {
            @Override
            public void onResponse(Call<List<ZillaParishad>> call, Response<List<ZillaParishad>> response) {
                List<ZillaParishad> list = response.body();
                zpList = new ArrayList<>();
                zpList.addAll(list);
                zpSearchList = new ArrayList<>();
                for(ZillaParishad zp: zpList){
                    if(zp.getDivision().equals(division) && zp.getDistrict().equals(district) && zp.getElection_program_name().equals(EP) && zp.getElectoral_division_num().equals(ed)) {
                        zpSearchList.add(new ZillaParishad(zp.getName_of_electoral_div(),zp.getElectoral_div_id(),zp.getSr_no(),zp.getDownload(),zp.getFull_name(),zp.getRegisteration_no()));
                    }
                }

//                // Remove duplicates from ArrayList of Strings.
//                ArrayList<ZillaParishad> unique = removeDuplicates(zpList);
//                zpList = unique;
                if (mListener != null)
                    mListener.onZPList1Populated(zpSearchList);
            }

            @Override
            public void onFailure(Call<List<ZillaParishad>> call, Throwable t) {

            }
        });
    }

    static ArrayList<String> removeDuplicates(ArrayList<String> list) {

        // Store unique items in result.
        ArrayList<String> result = new ArrayList<>();

        // Record encountered Strings in HashSet.
        HashSet<String> set = new HashSet<>();

        // Loop over argument list.
        for (String item : list) {

            // If String is not in set, add it to the list and the set.
            if (!set.contains(item)) {
                result.add(item);
                set.add(item);
            }
        }
        return result;
    }
}

