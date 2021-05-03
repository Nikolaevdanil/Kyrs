package com.example.demo.service;

import com.example.demo.DemoApplication;
import com.example.demo.models.Manufacture;
import com.example.demo.models.Worker;
import com.example.demo.repozitory.WorkerRepozitory;
import com.example.demo.service.emailService.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Component
@Transactional
public class WorkerService implements WorkerServiceInterface{

    private WorkerRepozitory workerRepozitory;
    private EmailService emailService;
    private final Logger log = LoggerFactory.getLogger(WorkerService.class);
    @Autowired
    public WorkerService(WorkerRepozitory workerRepozitory, EmailService emailService) {
        this.workerRepozitory = workerRepozitory;
        this.emailService = emailService;
    }

    public WorkerService(WorkerRepozitory workerRepozitory) {
        this.workerRepozitory = workerRepozitory;
    }

    @Override
    public void addWorker(Worker worker) {
        log.info("Added new worker {}", worker);
//        emailService.sendMessageAboutWorker(worker);
        workerRepozitory.save(worker);
    }

    @Override
    public List<Worker> getAllWorker() {
        log.info("Found all workers");
        List<Worker> workers = workerRepozitory.findAll();
        return workers;
    }

    @Override
    public List<Worker> filterByFirstName() {
        log.info("Sorted worker list by firstName");
        return workerRepozitory.findByOrderByFirstName();
    }

    @Override
    public List<Worker> filterByLastName() {
        log.info("Sorted worker list by lastName");
        return workerRepozitory.findByOrderByLastName();
    }

    @Override
    public List<Worker> filterByMiddleName() {
        log.info("Sorted worker list by middleName");
        return workerRepozitory.findByOrderByMiddleName();
    }

    @Override
    public List<Worker> filterByWorId() {
        log.info("Sorted worker list by id");
        return workerRepozitory.findByOrderById();
    }


    @Override
    public Worker getWorkerById(Long id) {
        log.info("Found a worker with id {}", id);
        return workerRepozitory.getOne(id);
    }

    @Override
    public void deleteWorkerById(Long id) {
        log.info("Deleted worker wit id {}", id);
        workerRepozitory.deleteById(id);
    }

    @Override
    public void deleteAllWorkers() {
        log.info("All workers deleted");
        workerRepozitory.deleteAll();
    }

    @Override
    public Manufacture getManufactureByWorker(Long id) {
        log.info("Got worker by card");
        return workerRepozitory.findById(id).orElseThrow(() ->
                new IllegalStateException("Worker with this id not found")).getManufacture();
    }

    @Override
    public Long getWorkerId(String firstName) {
        log.info("Found id of the worker with the worker number {}", firstName);
        return workerRepozitory.findByFirstName(firstName).getId();
    }

    @Override
    public String printWorkers(List<Worker> list) {
        String buff ="";
        int i = 0;
        buff += "<h>Workers</h><br/>";
        for (Worker item: list) {
            buff += "<tr><td>"+item.getId()+" - "+ item.getFirstName()+"  " + item.getLastName() + "  " + item.getMiddleName()+"<br/></td></tr>";
            i++;
        }
        if (i == 0)
        {
            buff+= "<tr><td>Worker list is empty</td></tr>";
        }
        return buff;
    }
}
