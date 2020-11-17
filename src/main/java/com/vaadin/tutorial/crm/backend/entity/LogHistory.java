package com.vaadin.tutorial.crm.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.vaadin.tutorial.crm.backend.util.DateHandler;

import java.util.Date;

public class LogHistory {


    private Long id;


    private Employee employee;

    private String principal;


    private String operationDetail;


    private String modifiedBy;

    @JsonFormat(pattern="dd-MM-yyyy")
    @JsonDeserialize(using = DateHandler.class)
    private Date modifiedDate;


    private String action;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getOperationDetail() {
        return operationDetail;
    }

    public void setOperationDetail(String operationDetail) {
        this.operationDetail = operationDetail;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPrincipal() {
        if(this.employee!=null){
         return "-> Employee "+ employee.getName();
        }

        return  "-> Empty Data";
    }

    @Override
    public String toString() {
        return "LogHistory{" +
                "id=" + id +
                ", employee=" + employee.toString() +
                ", principal='" + principal + '\'' +
                ", operationDetail='" + operationDetail + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", modifiedDate=" + modifiedDate +
                ", action='" + action + '\'' +
                '}';
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }
}
