package com.example.bank;

import java.util.ArrayList;

public class Constant {

    public static ArrayList<BannerData> getBanner() {
        ArrayList<BannerData> list = new ArrayList<BannerData>();
        BannerData data = new BannerData(R.drawable.img_3);
        list.add(data);
        BannerData data1 = new BannerData(R.drawable.img_3);
        list.add(data1);
        BannerData data2 = new BannerData(R.drawable.img_3);
        list.add(data2);
        return list;
    }

    public static ArrayList<BobData> getBob() {
        ArrayList<BobData> list = new ArrayList<BobData>();
        BobData data = new BobData(R.drawable.qr);
        list.add(data);
        BobData data1 = new BobData(R.drawable.qr);
        list.add(data1);
        BobData data2 = new BobData(R.drawable.qr);
        list.add(data2);
        return list;
    }

    public static ArrayList<trend_data> getTrend() {
        ArrayList<trend_data> list = new ArrayList<trend_data>();
        trend_data data = new trend_data(R.drawable.qr, "QR Scan");
        list.add(data);
        trend_data data1 = new trend_data(R.drawable.qr, "QR Scan");
        list.add(data1);
        trend_data data2 = new trend_data(R.drawable.qr, "QR Scan");
        list.add(data2);
        return list;
    }

}
