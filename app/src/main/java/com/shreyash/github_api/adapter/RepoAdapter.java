package com.shreyash.github_api.adapter;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.shreyash.github_api.GsonStringConvertor;
import com.shreyash.github_api.R;
import com.shreyash.github_api.models.GithubRepoResponse;
import com.shreyash.github_api.models.GithubUsers;
import com.shreyash.github_api.ui.ReposFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.MyViewHolder> {
    private FragmentActivity activity;
    private List<GithubRepoResponse> githubUsers;


    public RepoAdapter(FragmentActivity activity, List<GithubRepoResponse> githubUsers) {
        this.activity = activity;
        this.githubUsers = githubUsers;

    }

    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        viewHolder.userName.setText(githubUsers.get(position).getFullName());
        viewHolder.userId.setText("Owner: "+githubUsers.get(position).getOwner().getLogin());




    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repo_lay, parent, false);
        return new MyViewHolder(itemView);
    }

    public int getItemCount() {
        return githubUsers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView userName;
        private TextView userId;
        ImageView userDp;
        LinearLayout layout;


        public MyViewHolder(View itemView) {
            super(itemView);
            userDp = itemView.findViewById(R.id.user_dp);
            userName = itemView.findViewById(R.id.repo_name);
            userId = itemView.findViewById(R.id.owner);
            layout = itemView.findViewById(R.id.lay);
            layout.setOnClickListener(this);


        }


        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            int id = view.getId();



        }
    }

}
