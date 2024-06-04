package com.example.bank;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class DashBoardActivity extends AppCompatActivity {
  RecyclerView recyclerView;
  RecyclerView trendRecycler;
  BottomNavigationView bottomNavigationView;
    Fragment fragment;
    ImageView setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        recyclerView=findViewById(R.id.reward_recycler);
        trendRecycler=findViewById(R.id.trend_recycler);
        bottomNavigationView=findViewById(R.id.bottomNavigationView);

        setting=findViewById(R.id.setting);

        setting.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), SettingActivity.class);
            startActivity(i);
        });

        bottomNavigationView.setBackground(null);

        ArrayList<BannerData> list = new ArrayList<>();

        list.add(new BannerData(R.drawable.img_3));
        list.add(new BannerData(R.drawable.img_3));
        list.add(new BannerData(R.drawable.img_3));
        list.add(new BannerData(R.drawable.img_3));
        list.add(new BannerData(R.drawable.img_3));

        RewardBannerAdapter rewardBannerAdapter = new RewardBannerAdapter(list);
        recyclerView.setAdapter(rewardBannerAdapter);
        int COLUMN_COUNT1 = 1;
        GridLayoutManager manager = new GridLayoutManager(this, COLUMN_COUNT1, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);

        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                if (manager.findLastCompletelyVisibleItemPosition() < (rewardBannerAdapter.getItemCount() - 1)) {
                    manager.smoothScrollToPosition(recyclerView, new RecyclerView.State(),
                            manager.findLastCompletelyVisibleItemPosition() + 1);
                } else {
                    manager.smoothScrollToPosition(recyclerView, new RecyclerView.State(), 0);
                }
            }

        }, 0, 5000);


        ArrayList<trend_data> list1 = new ArrayList<>();

        list1.add(new trend_data(R.drawable.qr));
        list1.add(new trend_data(R.drawable.qr));
        list1.add(new trend_data(R.drawable.qr));
        list1.add(new trend_data(R.drawable.qr));

        TrendsAdapter trendsAdapter = new TrendsAdapter(list1);
        trendRecycler.setAdapter(trendsAdapter);
        int COLUMN_COUNT2= 4;
        trendRecycler.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, COLUMN_COUNT2, GridLayoutManager.VERTICAL, false);
        trendRecycler.setLayoutManager(gridLayoutManager);
    }
}