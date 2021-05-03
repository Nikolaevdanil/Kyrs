package com.example.demo.service.schedulerService;

import com.example.demo.models.Manufacture;
import com.example.demo.models.Worker;
import com.example.demo.service.ManufactureService;
import com.example.demo.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
@Component
@RequiredArgsConstructor
@ManagedResource(objectName = "MyMBeans:category=MBeans, name=schedulerService")
public class schedulerService implements schedulerInterface {

    @Autowired
    ManufactureService manufactureService;
    @Autowired
    WorkerService workerService;

    @Async
    @Scheduled(fixedDelay = 1800000)
    @ManagedOperation(description = "Deletes all content of the directory and writes all data from the databases to it every 30 minutes")
    @Override
    public void createSQLCopy() {
        String path = "src/main/Text";

        File dir = new File(path);
        File[] files = dir.listFiles();
        for (File file: files) file.delete();

        try {
            File banks = new File(path + "/manufactures.txt");
            banks.createNewFile();
            try(FileWriter writer = new FileWriter(banks, true);){
                List<Manufacture> bankList = manufactureService.getAllManufacture();
                for (Manufacture item : bankList) {
                    writer.write("id "+item.getId()+ " name " + item.getName() + " address" + item.getAddress() + "\n");
                }
                writer.flush();
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }

            File cards = new File(path + "/workers.txt");
            cards.createNewFile();
            try(FileWriter writer = new FileWriter(cards, true);){
                List<Worker> cardList = workerService.getAllWorker();
                for (Worker item : cardList) {
                    writer.write("id "+item.getId()+ " card number " + item.getFirstName() + " code " + item.getLastName() +  " code " + item.getMiddleName() +" banks id " + item.getManufacture().getId() + "\n");
                }
                writer.flush();
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
