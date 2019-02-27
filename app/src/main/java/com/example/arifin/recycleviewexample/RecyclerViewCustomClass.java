package com.example.arifin.recycleviewexample;

public class RecyclerViewCustomClass {
    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }

    private String value1;
    private String value2;
    private String value3;

    public RecyclerViewCustomClass(String s,String s2,String s3){
        this.value1=s;
        this.value2=s2;
        this.value3=s3;
    }

    public String toString() {
        return "Value1" + value1 + "Value2" + value2 + "Value3" + value3;
    }
}
