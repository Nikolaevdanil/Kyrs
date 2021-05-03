package com.example.demo.repozitory;


import com.example.demo.models.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface WorkerRepozitory extends JpaRepository<Worker, Long> {
    List<Worker> findByOrderByFirstName();
    List<Worker> findByOrderByLastName();
    List<Worker> findByOrderByMiddleName();
    List<Worker> findByOrderById();
    Worker findByFirstName(String  firstName);
}
