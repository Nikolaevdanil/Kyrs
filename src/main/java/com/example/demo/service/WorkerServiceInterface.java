package com.example.demo.service;

import com.example.demo.models.Manufacture;
import com.example.demo.models.Worker;

import java.util.List;

public interface WorkerServiceInterface {
    void addWorker(Worker worker);
    List<Worker> getAllWorker();
    List<Worker> filterByFirstName();
    List<Worker> filterByLastName();
    List<Worker>filterByMiddleName();
    List<Worker> filterByWorId();
    Worker getWorkerById(Long id);
    void deleteWorkerById(Long id);
    void deleteAllWorkers();
    Manufacture getManufactureByWorker(Long id);
    Long getWorkerId(String firstName);
    String printWorkers(List<Worker> list);
}
