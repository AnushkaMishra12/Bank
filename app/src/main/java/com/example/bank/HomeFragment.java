package com.example.bank;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.reward_recycler);
        RecyclerView bobRecycler = view.findViewById(R.id.bob_recycler);
        RecyclerView trendRecycler = view.findViewById(R.id.trend_recycler);

        ArrayList<BannerData> list = Constant.getBanner();
        RewardBannerAdapter rewardBannerAdapter = new RewardBannerAdapter(list);
        recyclerView.setAdapter(rewardBannerAdapter);
        int COLUMN_COUNT1 = 1;
        GridLayoutManager manager = new GridLayoutManager(getContext(), COLUMN_COUNT1, GridLayoutManager.HORIZONTAL, false);
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



        ArrayList<BobData> list2 = Constant.getBob();
        BobAdapter bobAdapter = new BobAdapter(list2);
        bobRecycler.setAdapter(bobAdapter);
        int COLUMN_COUNT3 = 1;
        bobRecycler.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), COLUMN_COUNT3, GridLayoutManager.HORIZONTAL, false);
        bobRecycler.setLayoutManager(layoutManager);

        ArrayList<trend_data> list1 = Constant.getTrend();
        TrendsAdapter trendsAdapter = new TrendsAdapter(list1);
        trendRecycler.setAdapter(trendsAdapter);
        int COLUMN_COUNT2 = 4;
        trendRecycler.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), COLUMN_COUNT2, GridLayoutManager.VERTICAL, false);
        trendRecycler.setLayoutManager(gridLayoutManager);

    }
}