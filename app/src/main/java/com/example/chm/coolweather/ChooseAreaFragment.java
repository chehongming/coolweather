package com.example.chm.coolweather;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chm.coolweather.db.County;
import com.example.chm.coolweather.util.HttpUtil;
import com.example.chm.coolweather.util.Utility;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseAreaFragment extends Fragment {
    public static final int LEVEL_PROVINCE;
    private List<String> dataList=new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private EditText search_Text;
    private ListView listView;
    static {
        LEVEL_PROVINCE = 0;
    }

    public static final int LEVEL_CITY=1;

    private Button search_Button;
    private List<County> countyList;

    public ChooseAreaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.choose_area,container,false);
        search_Text=view.findViewById(R.id.search_text);
        search_Button=view.findViewById(R.id.button);
        listView=(ListView) view.findViewById(R.id.list_view);
        adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,dataList);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        search_Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
              queryCounty();
            }
        });
    }
    public void queryFromServer(String address){
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                   String responseText=response.body().toString();
                   boolean  result=false;
                   result= Utility.handleResponse(responseText);
                   if(result){
                       queryCounty();
                   }
            }
        });
    }
    public void queryCounty(){
        String inputText=search_Text.getText().toString();
        countyList= DataSupport.where("countyName LIKE ?","%"+inputText+"%").find(County.class);
        if(countyList.size()>0){
            dataList.clear();
            for(County county:countyList){
                String s=county.getCountryName()+','+county.getAdminArea()+','+county.getCid()+','+county.getCountyName();
                dataList.add(s);
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
        }
        else{
            String address="https://search.heweather.com/find?"+"location="+inputText+"&"+"key="+"efbad2910fda4aa2803856adb0865996";
            queryFromServer(address);
        }
    }
    }