package com.example.bank;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RewardBannerAdapter extends RecyclerView.Adapter<RewardBannerAdapter.Holder> {
    private final ArrayList<BannerData> list;

    public RewardBannerAdapter(ArrayList<BannerData> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.reward, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        BannerData bannerData = list.get(position);
        holder.banner_image.setImageResource(bannerData.getImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        private final ImageView banner_image;

        public Holder(@NonNull View itemView) {
            super(itemView);
            banner_image = itemView.findViewById(R.id.banner_img);

        }

    }

}
