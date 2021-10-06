package com.shreyash.github_api.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.shreyash.github_api.R;
import com.shreyash.github_api.adapter.UserAdapter;
import com.shreyash.github_api.models.APIUsers;
import com.shreyash.github_api.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UserAdapter.OnLastLocation, SwipeRefreshLayout.OnRefreshListener {

    RecyclerView recyclerView;
    UserViewModel userViewModel;
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout;

    ArrayList<APIUsers> apiuserslist;
    UserAdapter userAdapter;
    TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        error = findViewById(R.id.error);
        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(this);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        apiuserslist = new ArrayList<>();
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        userAdapter = new UserAdapter(this, apiuserslist);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(userAdapter);
        userViewModel.getUsersApiResponse(pageNo).observe(this, new Observer<List<APIUsers>>() {
            @Override
            public void onChanged(List<APIUsers> apiUsers) {
                swipeRefreshLayout.setRefreshing(false);
                if (apiUsers != null) {
                  /*  if (pageNo == 1) {
                        apiuserslist.clear();
                        apiuserslist.addAll(apiUsers);
                        userAdapter.notifyDataSetChanged();
                    } else if (pageNo > 1) {
                        apiuserslist.addAll(apiUsers);
                        userAdapter.notifyItemRangeChanged(position, apiUsers.size());
                    }*/
                    apiuserslist.clear();
                    apiuserslist.addAll(apiUsers);
                    userAdapter.notifyDataSetChanged();

                }
                progressBar.setVisibility(View.GONE);
            }
        });
/*
        userViewModel.getUsersApiResponseMutableLiveData().observe(this, new Observer<UsersApiResponse>() {
            @Override
            public void onChanged(UsersApiResponse usersApiResponse) {
                if (usersApiResponse != null) {
                    if (usersApiResponse.getData() != null && pageNo == 1) {
                        apiuserslist.clear();
                        apiuserslist.addAll(usersApiResponse.getData());
                        userAdapter.notifyDataSetChanged();
                    }
                    if (usersApiResponse.getData() != null && pageNo > 1) {
                        apiuserslist.addAll(usersApiResponse.getData());
                    }
                    userAdapter.notifyItemRangeChanged(position, usersApiResponse.getData().size());
                }
                progressBar.setVisibility(View.GONE);
            }
        });
*/

        userViewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                swipeRefreshLayout.setRefreshing(false);
                if (pageNo == 1 && !s.equals("connection error")) {
                    apiuserslist.clear();
                    userAdapter.notifyDataSetChanged();
                    error.setVisibility(View.VISIBLE);
                    error.setText(s);
                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

                }
                progressBar.setVisibility(View.GONE);
            }

        });
    }

    int pageNo = 1;
    int position = 0;

    @Override
    public void lastLocation(int pos) {
        error.setVisibility(View.GONE);
        pageNo++;
        this.position = pos;
        progressBar.setVisibility(View.VISIBLE);
        userViewModel.getUsersApiResponse(pageNo);

    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        pageNo = 1;
        position = 0;
        userViewModel.getUsersApiResponse(pageNo);
    }
}