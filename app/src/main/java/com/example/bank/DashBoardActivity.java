package com.example.bank;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class DashBoardActivity extends AppCompatActivity {
  RecyclerView recyclerView;
  RecyclerView trendRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        recyclerView=findViewById(R.id.reward_recycler);
        trendRecycler=findViewById(R.id.trend_recycler);

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


//        ArrayList<trend_data> list1 = new ArrayList<>();
//
//        list1.add(new trend_data(R.drawable.scan));
//        list1.add(new trend_data(R.drawable.scan));
//        list1.add(new trend_data(R.drawable.scan));
//        list1.add(new trend_data(R.drawable.scan));
//
//
//        TrendsAdapter trendsAdapter = new TrendsAdapter(list1);
//        trendRecycler.setAdapter(trendsAdapter);
//        int COLUMN_COUNT2= 1;
//        trendRecycler.setHasFixedSize(true);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, COLUMN_COUNT2, GridLayoutManager.HORIZONTAL, false);
//        trendRecycler.setLayoutManager(gridLayoutManager);
    }
}