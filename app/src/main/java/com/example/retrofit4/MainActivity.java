package com.example.retrofit4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private JsonPlaceHolderapi jsonPlaceHolderapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        //initialising the retrofit
        Retrofit retrofit = new Retrofit.Builder()                       //initialising the retrofit class
                .baseUrl("http://10.0.2.2:5000/")        //adding the base url of your api
                .addConverterFactory(GsonConverterFactory.create())      //adding the converter factory you're using
                .build();                                                //finally build


        //Curling the api
        jsonPlaceHolderapi = retrofit.create(JsonPlaceHolderapi.class); //creating your api instance

        Call<List<Users>> call = jsonPlaceHolderapi.getUsers();

        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {

                List<Users> users = response.body();

                for (Users user : users){
                    String content = "";
                    content += "EMAIL: " +user.getEmail()+ "\n";
                    content += "ID: " +user.getId()+ "\n";
                    content += "NAME: " +user.getName()+ "\n";
                    content += "PASSWORD: " +user.getPassword()+ "\n";
                    content += "SCHOOL: " +user.getSchool()+ "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
