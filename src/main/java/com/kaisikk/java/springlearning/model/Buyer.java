package com.kaisikk.java.springlearning.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.kaisikk.java.springlearning.view.View;

public class Buyer {

    private Long id;

    @JsonView(View.Min.class)
    private String name;

    @JsonView(View.Body.class)
    private String country;

    private Integer token;


    public Buyer(Long id, String name, String country, Integer token) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getToken() {
        return token;
    }

    public void setToken(Integer token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", token=" + token +
                '}';
    }
}
