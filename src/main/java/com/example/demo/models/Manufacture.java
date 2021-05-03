package com.example.demo.models;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "manufacturess")
@Getter
@Setter
@ToString
public class Manufacture {
    @Id
    @Column(name = "man_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="address")
    private String address;
    @OneToMany(mappedBy = "manufacture", fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Worker> workerList;


    public Manufacture(){}
    public Manufacture(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
