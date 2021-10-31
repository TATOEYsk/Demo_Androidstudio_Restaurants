package com.example.demo_restaurant;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Apilnterface {

    String JSONRESTAURANT = "https://order-plz.herokuapp.com/";

    @GET("restaurants.json")
    Call<String> getString();

}
