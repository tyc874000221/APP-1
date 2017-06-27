package com.tcode.demo.bean;


public class ListData {

    private String dataName;
    private int dataNum;

    public ListData() {
    }

    public ListData(String dataName, int dataNum) {
        this.dataName = dataName;
        this.dataNum = dataNum;
    }

    public int getDataNum() {
        return dataNum;
    }

    public void setDataNum(int dataNum) {
        this.dataNum = dataNum;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    @Override
    public String toString() {
        return "ListData{" +
                "dataName='" + dataName + '\'' +
                ", dataNum=" + dataNum +
                '}';
    }
}
