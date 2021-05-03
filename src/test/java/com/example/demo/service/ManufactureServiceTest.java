package com.example.demo.service;


import com.example.demo.models.Manufacture;
import com.example.demo.repozitory.ManufactureRepozitory;
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
public class ManufactureServiceTest {
    @Mock
    ManufactureRepozitory manufactureRepozitory;
    @Captor
    ArgumentCaptor<Manufacture> captor;
    @Test
    void getAll(){
        Manufacture manufacture = new Manufacture();
        manufacture.setName("Daniil");
        manufacture.setAddress("yl.Str 25");
        Manufacture manufacture1 = new Manufacture();
        manufacture1.setName("Apple");
        manufacture1.setAddress("yl.Str 301");
        Mockito.when(manufactureRepozitory.findAll()).thenReturn(List.of(manufacture,manufacture1));
        assertEquals(2, manufactureRepozitory.findAll().size());
    }
    @Test
    void create() {
        Manufacture manufacture = new Manufacture();
        manufacture.setName("Apple1");
        ManufactureService ss = new ManufactureService(manufactureRepozitory);
        ss.addManufacture(manufacture);
        Mockito.verify(manufactureRepozitory).save(captor.capture());
        Manufacture captured = captor.getValue();
        assertEquals("Apple1", captured.getName());
    }
}
