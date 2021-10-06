package com.shreyash.github_api.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.shreyash.github_api.R;
import com.shreyash.github_api.models.APIUsers;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    private FragmentActivity activity;
    private List<APIUsers> apiUsersList;
    OnLastLocation onLastLocation;


    public UserAdapter(FragmentActivity activity, List<APIUsers> apiUsersList) {
        this.activity = activity;
        this.apiUsersList = apiUsersList;
        this.onLastLocation = (OnLastLocation) activity;

    }

    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        viewHolder.userName.setText(apiUsersList.get(position).getFirstName() + " "
                + apiUsersList.get(position).getLastName());
        viewHolder.userId.setText(apiUsersList.get(position).getEmail());

        Picasso.get().load(apiUsersList.get(position).getAvatar()).into(viewHolder.userDp);

        if (position >= apiUsersList.size() - 1) {
            onLastLocation.lastLocation(position);
        }

    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_lay, parent, false);
        return new MyViewHolder(itemView);
    }

    public int getItemCount() {
        return apiUsersList.size();
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

            if (id == R.id.lay) {

            }

        }
    }

   public interface OnLastLocation {
        void lastLocation(int pos);
    }

}
