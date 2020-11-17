package com.vaadin.tutorial.crm.backend.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vaadin.tutorial.crm.backend.entity.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {


    @Value("${spring.backend.baseurl}")
    private String BACKEND_BASE_URL;
    public List<Employee> getAllEmployee() {

         try{
             System.out.println("Fetching all Comment objects through REST..");

             // Fetch from 3rd party API; configure fetch
             WebClient.RequestHeadersSpec<?> spec = WebClient.create().
                     get().uri(BACKEND_BASE_URL+"/showCurrentEmployees");

             // do fetch and map result
             List<Employee> employees = spec.retrieve().
                     toEntityList(Employee.class).block().getBody();

             System.out.println(String.format("Received items.", employees.size()));

             return employees;
         } catch (Exception e){
             System.out.println(String.format("Cannot Connect To API"));

         }

        return null;






    }


    //update list
    //if filter is empty show all details
    //search by filter if present
    public List<Employee> findAll(String stringFilter) {

        if (stringFilter == null || stringFilter.isEmpty()) {
            return getAllEmployee();
        } else {
            return searchEmployee(stringFilter);
        }
    }



    public List<Employee> searchEmployee(String filter) {


        try{
            System.out.println("searching employees");

            // Fetch from 3rd party API; configure fetch
            WebClient.RequestHeadersSpec<?> spec = WebClient.create().
                    get().uri(BACKEND_BASE_URL+"/search/"+filter);

            // do fetch and map result
            List<Employee> employees = spec.retrieve().
                    toEntityList(Employee.class).block().getBody();

            System.out.println(String.format("Received items.", employees.size()));

            return employees;
        } catch (Exception e){
            System.out.println(String.format("Cannot Connect To API"));

        }

        return null;






    }


    public List<Employee> getAllArchivedEmployee() {

        try{
            System.out.println("Fetching all Comment objects through REST..");

            // Fetch from 3rd party API; configure fetch
            WebClient.RequestHeadersSpec<?> spec = WebClient.create().
                    get().uri(BACKEND_BASE_URL+"/showArchivedEmployees");

            // do fetch and map result
            List<Employee> employees = spec.retrieve().
                    toEntityList(Employee.class).block().getBody();

            System.out.println(String.format("Received items.", employees.size()));

            return employees;
        } catch (Exception e){
            System.out.println(String.format("Cannot Connect To API"));

        }

        return null;






    }

    public void savemployee(Employee employee) {
        System.out.println(employee.toString());

        System.out.println("getting the ways");


        Gson gson = new GsonBuilder()
                .setDateFormat("dd-MM-yyyy")
                .create();

        String requestSpec2 = WebClient
                .create(BACKEND_BASE_URL)
                .post()
                .uri("/saveEmployee")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(BodyInserters.fromValue(gson.toJson(employee)))
                .retrieve()
                .bodyToMono(String.class)
                .block();


        System.out.println(gson.toJson(employee));
    }

    public List<String> populateGenders() {
        return Arrays.asList("Male", "Female");
    }
}
