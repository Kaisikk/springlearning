package com.kaisikk.java.springlearning.model.auto;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    private String city;

    private String street;

    private String country;
}
