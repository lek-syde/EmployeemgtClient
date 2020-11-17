package com.vaadin.tutorial.crm.log;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.tutorial.crm.backend.entity.Employee;
import com.vaadin.tutorial.crm.backend.entity.LogHistory;

public class LogForm extends FormLayout {

    LogHistory logHistory;
    TextArea summaryTF = new TextArea("Payload");
    Binder<LogHistory> binder = new Binder<>();

    public LogForm() {

       binder.forField(summaryTF).bind(LogHistory::toString,LogHistory::setAction);

        addClassName("customer-form");
        getStyle().set("margin","10px");
        add(summaryTF);

    }

    public LogHistory getLogHistory() {
        return logHistory;
    }

    public void setLogHistory(LogHistory logHistory) {
        this.logHistory = logHistory;
        binder.readBean(logHistory);
        
    }
}
