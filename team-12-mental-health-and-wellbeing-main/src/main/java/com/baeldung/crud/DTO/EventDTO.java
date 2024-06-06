package com.baeldung.crud.DTO;

import javax.persistence.Entity;
import java.util.Date;
public class EventDTO {
    private int event_id;
    private String event_name;
    private String event_content;
    private Date startDate;
    private Date endDate;
    private String imgpath;
    public EventDTO(int event_id,String event_name,String event_content,Date startDate,Date endDate,String imgpath){
        this.event_id=event_id;
        this.event_name=event_name;
        this.event_content=event_content;
        this.startDate=startDate;
        this.endDate=endDate;
        this.imgpath=imgpath;
    }
    public int getEvent_id() {return event_id;}
    public String getEvent_name() {return event_name;}
    public String getEvent_content() {return event_content;}
    public Date getStartDate() {return startDate;}
    public Date getEndDate() {return endDate;}
    public String getImgpath() {return imgpath;}
}
