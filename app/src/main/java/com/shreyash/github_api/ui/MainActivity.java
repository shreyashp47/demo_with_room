package com.shreyash.github_api.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shreyash.github_api.GithubViewModel;
import com.shreyash.github_api.R;
import com.shreyash.github_api.adapter.UserAdapter;
import com.shreyash.github_api.models.GitHubApiResponse;
import com.shreyash.github_api.models.GithubUsers;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    GithubViewModel githubViewModel;
    ProgressBar progressBar;
    SearchView searchView;
    ArrayList<GithubUsers> githubUsersList;
    UserAdapter userAdapter;
    TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = (SearchView) findViewById(R.id.search_box);
        error = findViewById(R.id.error);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        githubUsersList = new ArrayList<>();
        githubViewModel = ViewModelProviders.of(this).get(GithubViewModel.class);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if (query.length() > 2) {
                    progressBar.setVisibility(View.VISIBLE);
                    error.setVisibility(View.GONE);
                    githubViewModel.getGitHubApiResponseMutableLiveData(query);
                }else
                    Toast.makeText(MainActivity.this, "Type At least 3 letters", Toast.LENGTH_SHORT).show();
              /*  if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(MainActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }*/
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //    adapter.getFilter().filter(newText);
                return false;
            }
        });
        userAdapter = new UserAdapter(this, githubUsersList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(userAdapter);
        githubViewModel.getGitHubApiResponseMutableLiveData().observe(this, new Observer<GitHubApiResponse>() {
            @Override
            public void onChanged(GitHubApiResponse gitHubApiResponse) {
                if (gitHubApiResponse != null) {
                    githubUsersList.clear();
                    githubUsersList.addAll(gitHubApiResponse.getItems());
                    userAdapter.notifyDataSetChanged();
                }
                progressBar.setVisibility(View.GONE);
            }
        });

        githubViewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                githubUsersList.clear();
                userAdapter.notifyDataSetChanged();
                error.setVisibility(View.VISIBLE);
                error.setText(s);
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);  }

        });
    }
}