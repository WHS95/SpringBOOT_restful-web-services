package com.Alex.rest.webservices.restfulwebservices.filtering;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SomeBean {

    //@JsonProperty를 사용하면 해당 값의 명칭을 변화 할 수있다.
    @JsonProperty("customBean")
    private  String bean1;
    private  String bean2;

    //@JsonIgnore을 사용하면 해당 값을 반환 되지 않느다.
    @JsonIgnore
    private  String bean3;


    public SomeBean(String bean1, String bean2, String bean3) {
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

