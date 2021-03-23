package com.zoolife.app.models;

import java.util.ArrayList;

public class SliderModel {

    private ArrayList<Data> data;

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SliderModel{" +
                "data=" + data +
                '}';
    }
}
