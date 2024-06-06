package com.baeldung.crud.repositories;

import com.baeldung.crud.DTO.EventDTO;
import com.baeldung.crud.form.AddEventForm;
import com.baeldung.crud.model.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EventRepositoryJDBC implements EventRepository{
    private  JdbcTemplate jdbcTemplate;

    @Autowired
    public EventRepositoryJDBC(JdbcTemplate aTemplate){
        jdbcTemplate=aTemplate;
    }



    @Override
    public List<EventDTO> findAllEvents() {
        return jdbcTemplate.query(
                "select * from events order by event_id desc" ,
                new EventMapper());
    }

    @Override
    public boolean addEvent(AddEventForm addEventForm, String imgPath) {
        int rows = jdbcTemplate.update(
                "insert into events (event_name,event_content,startDate,endDate,imgpath) values(?,?,?,?,?)" ,
                new Object[]{addEventForm.getEvent_name(),addEventForm.getEvent_content(),
                        addEventForm.getStartDate(),addEventForm.getEndDate(),imgPath});
        return rows>0;
    }

    @Override
    public boolean deleteEvent(int event_id) {
        String sql = "delete from events where event_id=?";
        int rows =jdbcTemplate.update(sql,event_id);
        return rows>0;
    }
}





