package com.Alex.rest.webservices.restfulwebservices.filtering;


import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("SomeBeanFilter")
public class SomeBeanDynic {

    private  String bean1;
    private  String bean2;
    private  String bean3;

    public SomeBeanDynic(String bean1, String bean2, String bean3) {
        this.bean1 = bean1;
        this.bean2 = bean2;
        this.bean3 = bean3;
    }

    public String getBean1() {
        return bean1;
    }

    public String getBean2() {
        return bean2;
    }

    public String getBean3() {
        return bean3;
    }

    @Override
    public String toString() {
        return "SomeBean{" +
                "bean1='" + bean1 + '\'' +
                ", bean2='" + bean2 + '\'' +
                ", bean3='" + bean3 + '\'' +
                '}';
    }
}

