package com.tcode.demo.bean;


public class ListData {

    private String dataName = "";
    private int dataNum = 0;
    private int isFooter = 0;

    public ListData() {
    }

    public ListData(String dataName, int dataNum,int isFooter) {
        this.dataName = dataName;
        this.dataNum = dataNum;
        this.isFooter = isFooter;
    }

    public int getIsFooter() {
        return isFooter;
    }

    public void setIsFooter(int isFooter) {
        this.isFooter = isFooter;
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
                ", isFooter=" + isFooter +
                '}';
    }
}
