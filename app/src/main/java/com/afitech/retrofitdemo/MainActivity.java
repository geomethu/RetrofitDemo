package com.afitech.retrofitdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.afitech.retrofitdemo.data.api.ApiService;
import com.afitech.retrofitdemo.data.api.UserApi;
import com.afitech.retrofitdemo.data.model.Post;
import com.afitech.retrofitdemo.data.model.User;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    static final String TAG = "MainActivity";
    UserApi userApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userApi = ApiService.userApiClient;

        Button btnUserList = findViewById(R.id.btnUserList);
        Button btnAddUser = findViewById(R.id.btnAddUser);
        TextView tv = findViewById(R.id.textView);

        btnUserList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<List<User>> call = userApi.getUserList();
                call.enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        Log.e(TAG, response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Log.e(TAG, t.getMessage());
                        call.cancel();
                    }
                });
            }
        });


        btnAddUser.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Post p = new Post(1, "Test", "test body text");
                  Call<Post> call = userApi.addPost(p);

                  call.enqueue(new Callback<Post>() {
                      @Override
                      public void onResponse(Call<Post> call, Response<Post> response) {
                          assert response.body() != null;
                          Post p = response.body();
                          Log.e(TAG, p.toString());
                          Toast.makeText(MainActivity.this, "ID :: "+p.getId(), Toast.LENGTH_SHORT).show();
                          tv.setText(p.getId() +" "+p.getTitle());
                      }

                      @Override
                      public void onFailure(Call<Post> call, Throwable t) {
                          Log.e(TAG, t.getMessage());
                          call.cancel();
                      }
                  });
              }
          }

        );


    }
}