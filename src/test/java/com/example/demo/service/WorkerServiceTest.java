package com.example.demo.service;



import com.example.demo.models.Worker;
import com.example.demo.repozitory.WorkerRepozitory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class WorkerServiceTest {
    @Mock
    WorkerRepozitory workerRepozitory;
    @Captor
    ArgumentCaptor<Worker> captor;
    @Test
    void getAll(){
        Worker worker = new Worker();
        worker.setFirstName("Danil");
        worker.setLastName("Nikolaev");
        worker.setMiddleName("Alexe");
        Worker worker1 = new Worker();
        worker1.setFirstName("Pavel");
        worker1.setLastName("Eshimov");
        worker1.setMiddleName("Mark");
        Mockito.when(workerRepozitory.findAll()).thenReturn(List.of(worker,worker1));
        assertEquals(2, workerRepozitory.findAll().size());
    }
    @Test
    void create() {
        Worker worker = new Worker();
        worker.setFirstName("Даниил");
        WorkerService ss = new WorkerService(workerRepozitory);
        ss.addWorker(worker);
        Mockito.verify(workerRepozitory).save(captor.capture());
        Worker captured = captor.getValue();
        assertEquals("Даниил", captured.getFirstName());
    }
}