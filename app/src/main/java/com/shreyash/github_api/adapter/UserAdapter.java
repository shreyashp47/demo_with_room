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
import com.shreyash.github_api.models.GithubUsers;
import com.shreyash.github_api.ui.ReposFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    private FragmentActivity activity;
    private List<GithubUsers> githubUsers;


    public UserAdapter(FragmentActivity activity, List<GithubUsers> githubUsers) {
        this.activity = activity;
        this.githubUsers = githubUsers;

    }

    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        viewHolder.userName.setText(githubUsers.get(position).getLogin());

        Picasso.get().load(githubUsers.get(position).getAvatarUrl()).into(viewHolder.userDp);


    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_lay, parent, false);
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
            userName = itemView.findViewById(R.id.user_name);
            userId = itemView.findViewById(R.id.user_id);
            layout = itemView.findViewById(R.id.lay);
            layout.setOnClickListener(this);


        }


        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            int id = view.getId();

            if (id==R.id.lay){
                ReposFragment hotelBookingDetailsFragment = new ReposFragment();
                Bundle args = new Bundle();
                args.putString("user", githubUsers.get(position).getLogin());
                hotelBookingDetailsFragment.setArguments(args);
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, hotelBookingDetailsFragment)
                        .addToBackStack(null)
                        .commit();
            }

        }
    }

}
