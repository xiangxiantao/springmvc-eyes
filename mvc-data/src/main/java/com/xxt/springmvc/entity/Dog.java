package com.xxt.springmvc.entity;

public class Dog {

    private String dName;

    private Integer dAge;

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public Integer getdAge() {
        return dAge;
    }

    public void setdAge(Integer dAge) {
        this.dAge = dAge;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "dName='" + dName + '\'' +
                ", dAge=" + dAge +
                '}';
    }
}
