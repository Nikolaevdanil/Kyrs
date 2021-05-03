package com.example.demo.service.emailService;

import com.example.demo.models.Manufacture;
import com.example.demo.models.Worker;

public interface EmailServiceInterface {
    void sendMessageAboutManufacture(Manufacture manufacture);

    void sendMessageAboutWorker(Worker worker);
}
