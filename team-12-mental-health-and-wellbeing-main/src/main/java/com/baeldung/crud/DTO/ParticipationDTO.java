package com.baeldung.crud.DTO;
import javax.xml.crypto.Data;
import java.util.Date;

public class ParticipationDTO {
    private int participation_id;
    private String user_name;
    private int event_id;
    private Date participation_date;

    public ParticipationDTO(int participation_id, String user_name, int event_id, Date participation_date){
        this.participation_id=participation_id;
        this.user_name=user_name;
        this.event_id=event_id;
        this.participation_date=participation_date;
    }

    public int getParticipation_id() {return participation_id;}
    public String getUser_name() {return user_name;}
    public int getEvent_id() {return event_id;}
    public Date getParticipation_date() {return participation_date;}
}
