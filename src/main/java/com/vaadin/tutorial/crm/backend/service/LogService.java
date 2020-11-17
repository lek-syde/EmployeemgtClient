package com.vaadin.tutorial.crm.backend.service;


import com.vaadin.tutorial.crm.backend.entity.LogHistory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class LogService {
    @Value("${spring.backend.baseurl}")
    private String BACKEND_BASE_URL;

    public List<LogHistory> getAllLogs() {

        try{
            System.out.println("Fetching all Comment objects through REST..");

            // Fetch from 3rd party API; configure fetch
            WebClient.RequestHeadersSpec<?> spec = WebClient.create().
                    get().uri(BACKEND_BASE_URL+"/logs");

            // do fetch and map result
            List<LogHistory> logs = spec.retrieve().
                    toEntityList(LogHistory.class).block().getBody();

            System.out.println(String.format("Received items.", logs.size()));

            return logs;
        } catch (Exception e){
           e.printStackTrace();

        }

        return null;






    }

}
