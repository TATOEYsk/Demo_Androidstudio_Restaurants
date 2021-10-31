package com.example.demo_restaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    private  RogerAdapter rogerAdapter;
    private  RecyclerView recyclerView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        fetchJSON();
    }

    private void fetchJSON() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Apilnterface.JSONRESTAURANT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        Apilnterface api = retrofit.create(Apilnterface.class);
        Call<String> call = api.getString();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()){
                    if (response.body() != null){
                        Log.i("onSuccess", response.body().toString());

                        String jasonresponse = response.body().toString();
                        writeTv(jasonresponse);
                    }else {
                        Log.i("onEmptyResponse", "Returned  empty response");
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void writeTv(String response) {

        try{
            JSONObject obj = new JSONObject(response);
            if(obj.optString("restaurants").equals("true")){

                ArrayList<RestaurantModel> retroModelArrayList = new ArrayList<>();
                JSONArray dataArray  = obj.getJSONArray("data");

                for (int i = 0; i < dataArray.length(); i++) {

                    RestaurantModel restaurantModel = new RestaurantModel();
                    JSONObject dataobj = dataArray.getJSONObject(i);

                    restaurantModel.setid(dataobj.getString("id"));
                    restaurantModel.setName(dataobj.getString("name"));
                    restaurantModel.setImage_url(dataobj.getString("Image_url"));
                    restaurantModel.setKind(dataobj.getString("Kind"));

                    retroModelArrayList.add(restaurantModel);

                }

                recyclerView = findViewById(R.id.recycler);
                rogerAdapter = new RogerAdapter(this,retroModelArrayList);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(rogerAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false));


            }else {
                Toast.makeText(MainActivity.this, obj.optString("message")+"", Toast.LENGTH_SHORT).show();
            }

        }catch (JSONException e) {
            e.printStackTrace();
        }

    }

}