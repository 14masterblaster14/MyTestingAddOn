package com.example.a37recyclerview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by comp on 2/23/2017.
 */

public class MyData {

    private int imageID;
    private String title;

    public static List<MyData> getObjectList() {

        List<MyData> dataList = new ArrayList<>();
        int[] images = getImages();

        for (int i = 0; i < images.length; i++) {

            MyData myData = new MyData();
            myData.setImageID(images[i]);
            myData.setTitle("Picture " + i);
            dataList.add(myData);
        }

        return dataList;
    }

    private static int[] getImages() {

        int[] images = {R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5,
                R.drawable.s6, R.drawable.s7, R.drawable.s8, R.drawable.s9, R.drawable.s10,
                R.drawable.s11, R.drawable.s12, R.drawable.s13, R.drawable.s14, R.drawable.s15,
                R.drawable.s16, R.drawable.s17, R.drawable.s19, R.drawable.s20, R.drawable.s21};
        return images;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
