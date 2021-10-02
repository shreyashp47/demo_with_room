package com.shreyash.github_api.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shreyash.github_api.GithubViewModel;
import com.shreyash.github_api.R;
import com.shreyash.github_api.adapter.RepoAdapter;
import com.shreyash.github_api.models.GithubRepoResponse;

import java.util.ArrayList;
import java.util.List;

public class ReposFragment extends Fragment {
    RecyclerView recyclerView;
    GithubViewModel githubViewModel;
    ProgressBar progressBar;

    ArrayList<GithubRepoResponse> arrayList;
    RepoAdapter repoAdapter;
    TextView error;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_repos, container, false);

        error = view.findViewById(R.id.error);
        progressBar = view.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        arrayList = new ArrayList<>();
        githubViewModel = ViewModelProviders.of(this).get(GithubViewModel.class);

        repoAdapter = new RepoAdapter(getActivity(), arrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(repoAdapter);
        githubViewModel.getListMutableLiveData(getArguments().getString("user"));
        githubViewModel.getListMutableLiveData().observe(getActivity(), new Observer<List<GithubRepoResponse>>() {
            @Override
            public void onChanged(List<GithubRepoResponse> githubRepoResponses) {
                if (githubRepoResponses != null) {
                    arrayList.clear();
                    arrayList.addAll(githubRepoResponses);
                    repoAdapter.notifyDataSetChanged();
                }
                progressBar.setVisibility(View.GONE);
            }

        });



        githubViewModel.getError().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                arrayList.clear();
                repoAdapter.notifyDataSetChanged();
                error.setVisibility(View.VISIBLE);
                error.setText(s);
                Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);   }
        });
        return view;
    }

}