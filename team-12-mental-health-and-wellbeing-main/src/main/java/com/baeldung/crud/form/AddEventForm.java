package com.baeldung.crud.form;

public class AddEventForm {
    private String event_name;
    private String event_content;
    private String startDate;
    private String endDate;

    public AddEventForm(String event_name,String event_content,String startDate,String endDate){
        this.event_name=event_name;
        this.event_content=event_content;
        this.startDate=startDate;
        this.endDate=endDate;
    }

    public String getEvent_name() {return event_name;}
    public String getEvent_content() {return event_content;}
    public String getStartDate() {return startDate;}
    public String getEndDate() {return endDate;}
}
