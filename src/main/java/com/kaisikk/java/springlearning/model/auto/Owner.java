package com.kaisikk.java.springlearning.model.auto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Address address;

    @OneToMany(mappedBy = "owner")
    private List<Car> cars = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "gym_owner", joinColumns = @JoinColumn(name = "gym_id"), inverseJoinColumns = @JoinColumn(name = "owner_id"))
    private List<Gym> gyms = new ArrayList<>();

}
