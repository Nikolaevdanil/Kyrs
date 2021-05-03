package com.example.demo.service;

import com.example.demo.models.Manufacture;

import java.util.List;

public interface ManufactureServiceInterface {
    void addManufacture(Manufacture manufacture);
    List<Manufacture> getAllManufacture();
    List<Manufacture> filterByName();
    List<Manufacture> filterByAddress();
    List<Manufacture> filterByManId();
    Manufacture getManufactureById(Long id);
    void deleteManufactureById(Long id);
    void deleteAllManufactures();
    Long getManufactureId(String name);
    String printManufactures(List<Manufacture> list);
}
