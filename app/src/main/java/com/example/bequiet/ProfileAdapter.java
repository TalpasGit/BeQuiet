package com.example.bequiet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {
    private ArrayList<Profile> profiles;


    public static class ProfileViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView titleView;
        public TextView descriptionView;
        public ImageView moveToTrash;
        public ProfileAdapter test1;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            titleView = itemView.findViewById(R.id.text_title);
            descriptionView = itemView.findViewById(R.id.text_description);
            moveToTrash = itemView.findViewById(R.id.move_to_trash);



        }

    }

    public ProfileAdapter(ArrayList<Profile> profiles) {
        this.profiles = profiles;
    }


    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.profiles, parent, false);
        ProfileViewHolder pvh = new ProfileViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ProfileViewHolder holder, int position) {
        Profile currentItem = profiles.get(position);

        holder.imageView.setImageResource(currentItem.getImageResource());
        holder.titleView.setText(currentItem.getTitle());
        holder.descriptionView.setText(currentItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }
}
